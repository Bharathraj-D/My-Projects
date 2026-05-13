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

const holderName=document.getElementById("holderName");
const accountType=document.getElementById("accountType");
const branchDetail=document.getElementById("branch");
const phoneNoDetail=document.getElementById("phoneNo");
const aadharNoDetail=document.getElementById("aadharNo");
const addressDetail=document.getElementById("address");
// const create=document.getElementById("create");
async function createAccount(event){
    try {
        event.preventDefault(); 
        let name=holderName.value;
        let type=accountType.value;
        let branch=branchDetail.value;
        let phoneNo=phoneNoDetail.value;
        let aadharNo=aadharNoDetail.value;
        let address=addressDetail.value;
        const data=await fetch("http://localhost:8080/api/create",{
         method:"POST",
         headers:{
            "content-type":"application/json"
         },
         body:JSON.stringify({
             "accountNo":0,
             "holderName":name,
             "accountType":type,
             "branch":branch,
             "balance":0,
             "phoneNo":phoneNo,
             "aadharNo":aadharNo,
             "address":address
         })
        })
        if(!data.ok) {
            let errText = "Failed to create account.";
            if (data.status === 400) errText = "Account already exists with these details.";
            throw new Error(errText);
        }
        window.alert("Account Created");
        window.location.href="Accounts.html";
    } catch(err) {
        showError(err.message);
    }
}

