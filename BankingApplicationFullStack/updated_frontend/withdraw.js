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

const withdraw=document.getElementById("withdraw");
const account=document.getElementById("account");
const amount=document.getElementById("amount");
withdraw.onclick= async function(){
    try {
         let wamount=Number(amount.value);
         let accountNo=Number(account.value);
         console.log("Clicked");
         if(wamount<=0){
            console.log("Empty");
            return;
         }
         else{
            console.log("Entered");
            let res=await fetch(`http://localhost:8080/api/transaction`,{
               method:"POST",
               headers: {
                   "Content-Type": "application/json"
               },
               body: JSON.stringify({
                   type: "Withdrawal",
                   accNo: accountNo,
                   amount: wamount
               })
            });
            if(!res.ok) {
               let errText = "Withdrawal failed.";
               if (res.status === 400) errText = "Invalid amount or insufficient funds.";
               if (res.status === 404) errText = "Account not found.";
               throw new Error(errText);
            }
            console.log(res);
            window.alert("Withdrawel Successfull!");
            window.location.href="home.html";
         }
    } catch(err) {
         showError(err.message);
    }
}