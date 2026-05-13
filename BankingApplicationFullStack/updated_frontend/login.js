const admin=document.getElementById("admin");
const user=document.getElementById("user");
const accountNo=document.getElementById("accountNo");
const password=document.getElementById("password");
const login=document.getElementById("login");
const Enter=document.getElementById("Enter");
admin.addEventListener("change",()=>{
    if(admin.checked){
       Enter.textContent="Enter the Admin id :";
       password.placeholder="Enter Password";
    }
});
user.addEventListener("change",()=>{
    if(user.checked){
        Enter.textContent="Enter your Account no:";
        password.placeholder="Password";
    }
});
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
async function getUser (adminId,password) {
    const result= await fetch(`http://localhost:8080/api/account`,{
        method:"POST",
        headers:{
            "content-type":"application/json"   },
        body:JSON.stringify({
            "accountNo":adminId,
            "password":password
        })
    });
    console.log(result);
    if(!result.ok){
        let errText = "Login failed.";
        if(result.status === 404) errText = "User not found.";
        if(result.status === 401) errText = "Invalid password.";
        throw new Error(errText);
    }
    else{
    let res= await result.json();
    return res;
    }

}
login.onclick= async function(){
    if(admin.checked){
        let adminId=accountNo.value;
        let adminPassword=Number(password.value);
        if(adminId=="bharath"&&adminPassword==1234){
            window.location.href="home.html";
        }
        else{
            showError("Wrong Id or Password");
        }
    }
    else if(user.checked){
      
        let adminId=Number(accountNo.value);
        let adminPassword=password.value;
        try{
        const accountDetails=await getUser(adminId,adminPassword);
        sessionStorage.setItem("userPassword", adminPassword);
            window.location.href=`Account.html?accNo=${adminId}`;
    }catch(error){
        showError(error.message);
    }
    }
    else{
        showError("Select something");
    }
}
