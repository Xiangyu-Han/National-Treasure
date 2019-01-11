
function namelength() {
    var no = document.getElementById("uId").value;
    if(no.length>4&&no.length<13) {
        document.getElementById("tishi1").innerHTML="<font color='green'> </font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi1").innerHTML="<font color='red'>请输入5-12位由数字组成的账号</font>";
        document.getElementById("submit").disabled = true;
    }
}

function passwordlength() {
    var pw1 = document.getElementById("password").value;
    if(pw1.length>4&&pw1.length<13) {
        document.getElementById("tishi2").innerHTML="<font color='green'> </font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi2").innerHTML="<font color='red'>请输入5-12位由字母或数字组成的密码</font>";
        document.getElementById("submit").disabled = true;
    }
}

function wsh() {
    var noo = document.getElementById("uName").value;
    if(noo.length<6) {
        document.getElementById("tishi4").innerHTML="<font color='green'> </font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi4").innerHTML="<font color='red'>请输入最多长度为5位的昵称</font>";
        document.getElementById("submit").disabled = true;
    }
}

function passsame() {
    var pw1 = document.getElementById("password").value;
    var pw2 = document.getElementById("password2").value;
    if(pw1==pw2) {
        document.getElementById("tishi3").innerHTML="<font color='green'> </font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi3").innerHTML="<font color='red'>两次密码不相同</font>";
        document.getElementById("submit").disabled = true;
    }
}