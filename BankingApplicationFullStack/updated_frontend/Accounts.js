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

const accountList=document.getElementById("accountList");
async function loadAccounts(){
    try {
        let data=await fetch("http://localhost:8080/api/accounts",
            {
                method:"GET"
            });
        if(!data.ok) {
            throw new Error("Failed to load accounts. Server error.");
        }
        let res=await data.json();
        console.log(data);
        console.log(res);
        res.forEach((element)=>{
            let list=document.createElement("li");
            let division=document.createElement("div")
            division.className="account";
            division.innerHTML=`
            <p class="account-inside-para">${element.branch}</p>
            <p class="account-inside-para">${element.accountNo}</p>
            <p class="account-inside-para">${element.holderName}</p>
            <a href="AdminAccount.html?accNo=${element.accountNo}" style="text-decoration:none;"><button style="width:100%; height:100%; background-color:grey;color:white;">View</button></a>
            `;
            list.appendChild(division);
            accountList.appendChild(list);
        });
    } catch(err) {
        showError(err.message);
    }
}
loadAccounts();