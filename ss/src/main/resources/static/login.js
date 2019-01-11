// JavaScript Document
function login(){
	var Userid = document.getElementById("userid").value.trim();
	var Password = document.getElementById("password").value.trim();
	var reg = /^[0-9]{5,12}$/;
	var Preg = /^[a-z0-9_]+$/;
	if(Userid.length < 5 || Userid.length ==0 ||!reg.test(Userid)||Userid.value==""){
		alert("输入的UserID不正确，请重新输入！");
		return false;
	}
	if(Password.length < 5 || Password.length ==0 ||!Preg.test(Password)){
		alert("输入的Password不正确，请重新输入！");
		return false;
	}
}

