const param=new URLSearchParams(window.location.search);
const accountNum=Number(param.get("accNo"));

const nameDetails=document.getElementById("nameDetails");
const balance=document.getElementById("balance");
const accountNo=document.getElementById("accountNo");
const branch=document.getElementById("branch");
const accountType=document.getElementById("accounType");
const aadharNo=document.getElementById("aadharNo");
const phoneNo=document.getElementById("phoneNo");
const address=document.getElementById("address");
const transactions=document.getElementById("transactions");
const sendMoney=document.getElementById("sendMoney");

function showError(message) {
    let msg = document.getElementById("msg");
    if(!msg) {
        msg = document.createElement("div");
        msg.id = "msg";
        msg.style.color = "white";
        msg.style.fontWeight = "bold";
        msg.style.padding = "15px 30px";
        msg.style.textAlign = "center";
        msg.style.position = "fixed";
        msg.style.top = "20px";
        msg.style.left = "50%";
        msg.style.transform = "translateX(-50%)";
        msg.style.backgroundColor = "#ff4d4d";
        msg.style.borderRadius = "8px";
        msg.style.boxShadow = "0 4px 15px rgba(0,0,0,0.2)";
        msg.style.zIndex = "9999";
        msg.style.fontSize = "16px";
        msg.style.transition = "opacity 0.3s ease-in-out";
        document.body.appendChild(msg);
    }
    msg.style.display = "block";
    msg.style.opacity = "1";
    msg.textContent = message;
    setTimeout(() => { 
        msg.style.opacity = "0"; 
        setTimeout(() => { msg.style.display = "none"; }, 300);
    }, 5000);
}

async function load(){
    try {
        const password = sessionStorage.getItem("userPassword");
        const data= await fetch(`http://localhost:8080/api/account`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({"accountNo": accountNum, "password": password})
        });
        if(!data.ok) {
            let errText = "Failed to load account details.";
            if (data.status === 404) errText = "Account not found.";
            if (data.status === 401) errText = "Unauthorized: Invalid password.";
            throw new Error(errText);
        }
        const res=await data.json();
        console.log(res);
        nameDetails.textContent=res.holderName;
        balance.textContent=res.balance;
        accountNo.textContent=res.accountNo;
        branch.textContent=res.branch;
        accountType.textContent=res.accountType;
        aadharNo.textContent=res.aadharNo;
        phoneNo.textContent=res.phoneNo;
        address.textContent=res.address;
    } catch(err) {
        showError(err.message);
    }
}
load();

async function loadTransactions(){
    try {
        const result=await fetch(`http://localhost:8080/api/Transactions/${accountNum}`,{
            method:"GET"
        });
        if(!result.ok) {
            throw new Error("Failed to load transactions.");
        }
        const res=await result.json();
        transactions.innerHTML=`
              <tr>
                <th>Transaction Id</th>
                <th>Type</th>
                <th>Account No</th>
                <th>Amount</th>
                <th>Current Balance</th>
              </tr>
        `;
        res.forEach(element => {
            let row=document.createElement("tr");
            row.innerHTML=
            `
            <td>${element.transactionId}</td>
            <td>${element.type}</td>
            <td>${element.accNo}</td>
            <td>${element.amount}</td>
            <td>${element.currentBalance}</td>
            `;
            row.style.fontSize="18px";
            transactions.appendChild(row);
        });
    } catch(err) {
        showError(err.message);
    }
}
loadTransactions();

let send=document.getElementById("send");
sendMoney.onclick=function(){
    send.style.visibility="visible";
}
const cancel=document.getElementById("cancel");
cancel.onclick=function(){
    send.style.visibility="hidden";
}
const sendbutton=document.getElementById("sendbutton");
sendbutton.onclick= async function(){
    try {
        let account=document.getElementById("account").value;
        let amount=document.getElementById("amount").value;
        let data= await fetch("http://localhost:8080/api/MakeTransaction",{
            method:"POST",
            headers:{
                "content-Type":"application/json"
            },
            body:JSON.stringify(
                {
                  "senderId":accountNum,
                  "receiverId":account,
                  "amount":amount
                }
            )
        });
        if(!data.ok) {
            let errText = "Transaction failed.";
            if (data.status === 400) errText = "Invalid transaction: Check amounts and receiver details. (Insufficient funds or same account)";
            if (data.status === 404) errText = "Receiver or Sender account not found.";
            throw new Error(errText);
        }
        console.log(data);
        await loadTransactions();
        send.style.visibility="hidden";
        // Update balance after transaction
        await load();
    } catch(err) {
        showError(err.message);
    }
}
const logout=document.getElementById("logout");
logout.onclick=function(){
    sessionStorage.removeItem("userPassword");
    window.location.href="login.html";
}