function login_check() {
    var tele = document.getElementById("tele").value;
    var user_pwd = document.getElementById("user_pwd").value;
    let user_type;
    let radio = document.getElementsByName("用户类型");
    for(var i=0;i<radio.length;i++){
        if(radio[i].checked==true) {
            user_type=radio[i].value;
            break;
        }
    }
    var sss = {
        "tele":tele,
        "user_pwd":user_pwd,
        "user_type":user_type
    }
    sss = JSON.stringify(sss);
    console.log(sss);
    $.ajax({
        type:'POST',
        url:'http://120.79.70.13:7000/shop/loginCheck',
        data:sss,
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            console.log(data);
            if (data.code == "000000"){
                window.location.href = 'http://120.79.70.13:7000/shop/index';
            }else {
                alert(data.desc);
            }
        },
        error: function () {

        }
    })
}

function reg_check() {
    let user_name = document.getElementById("user_name").value;
    let user_pwd = document.getElementById("user_pwd").value;
    let user_pwd_confirm = document.getElementById("user_pwd_confirm").value;
    let user_type;
    let radio = document.getElementsByName("用户类型");
    for(var i=0;i<radio.length;i++){
        if(radio[i].checked==true) {
            user_type=radio[i].value;
            break;
        }
    }


    if (user_pwd !== user_pwd_confirm){
        alert("密码不一致");
        return;
    }
    let tele = document.getElementById("tele").value;

    let sss = {
        "user_name":user_name,
        "user_pwd":user_pwd,
        "tele":tele,
        "user_type":user_type
    }
    sss = JSON.stringify(sss)

    $.ajax({
        type:'post',
        url:"http://120.79.70.13:7000/shop/registryCheck",
        data:sss,
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            console.log(data);
            if (data.code == "000002"){
                reg_success();
            }else {
                alert(data.desc);
            }
        },
        error: function () {

        }
    })
}

function reg_success() {
    // debugger;
    let totalTime = 3;
    let msg = document.createElement('p');
    msg.className = 'message';
    msg.innerHTML = '注册成功，3秒后将跳转到登录页面';
    let intervalId = setInterval(() => {
        totalTime--;
        if(totalTime == 0) {
            // let toRemove = document.body.children;
            // document.body.removeChild(toRemove[toRemove.length - 1]);
            // clearInterval(intervalId);
            window.location.href = 'http://120.79.70.13:7000/shop/login';
        }
        msg.innerHTML = '注册成功，' + totalTime + '秒后将跳转到登录页面';

    }, 1000);
    document.body.appendChild(msg);
}

function changeUserType(type) {
    if (type == 1){
        document.getElementById("user_name").setAttribute("placeholder","用户名");
    } else {
        document.getElementById("user_name").setAttribute("placeholder","店铺名");
    }
}