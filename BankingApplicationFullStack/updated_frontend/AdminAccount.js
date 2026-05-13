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
        const data= await fetch(`http://localhost:8080/api/accounts`);
        if(!data.ok) {
            throw new Error("Failed to load accounts. Server error.");
        }
        const allAccounts = await data.json();
        const res = allAccounts.find(acc => acc.accountNo === accountNum);
        if(!res) throw new Error("Account not found");
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
        console.log(res);
    } catch(err) {
        showError(err.message);
    }
}
loadTransactions();