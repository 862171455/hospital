$(function(){
    //发送验证码
    $(".sendVerifyCode").on("click", function(){
        var tel = $("input[name=tel]").val();
        $.ajax({
            url:"/sendSms",
            async : true,
            type: "post",
            dataType: "json",
            data: {"tel":tel},
            success: function (data) {
                if(data == 'fail'){
                    alert("发送验证码失败");
                    return ;
                }
            }
        });
    })
    //提交
    $("#tj").on("click", function(){
        var data = {};
        data.username = $.trim($("input[name=username]").val());
        data.pwd = $.trim($("input[name=pwd]").val());
        data.tel = $.trim($("input[name=tel]").val());
        data.verifyCode = $.trim($("input[name=verifyCode]").val());
        $.ajax({
            url:"/register",
            async : true,
            type: "post",
            data: $("#my").serialize(),
            success: function (dat) {
                if(dat == 'fail'){
                    alert("注册失败");
                }else {
                    alert("注册成功");
                    return window.location.href='/for/qt_login';
                }
            }
        });
    })

    var x=0;
    var y=0;
    var z=0;
    $("#phone").change(function () {
        $.ajax({
            url: "/checkPhone",
            async: true,
            type: "post",
            data: $("#my").serialize(),
            success: function (data) {
                if(data == 'faill'){
                    $('#yz').attr("disabled","disabled");
                    $('#tj').attr("disabled","disabled");

                }
                 if(data == 'fail'){
                    alert("手机号已注册")
                    $("#phone").css("color","red");
                    $('#yz').attr("disabled","disabled");
                    $('#tj').attr("disabled","disabled");

                }
                if(data=='yes'){
                    $("#phone").css("color","black");
                    x=1;
                    alert(x);
                }
            }
        });
    })
    $("#name").change(function () {
        $.ajax({
            url: "/checkName",
            async: true,
            type: "post",
            data: $("#my").serialize(),
            success: function (data) {
                if(data == 'faill'){
                    $('#yz').attr("disabled","disabled");
                    $('#tj').attr("disabled","disabled");

                }else if(data == 'fail'){
                    alert("账号已存在")
                    $("#name").css("color","red");
                    $('#yz').attr("disabled","disabled");
                    $('#tj').attr("disabled","disabled");

                }
                if(data=='yes'){
                    $("#name").css("color","black");
                    y=1;
                    alert(y);
                }
            }
        });
    })
    $("#pwd").change(function () {
       z=x+y;
       if(z==2){
           $('#yz').removeAttr("disabled");
           $('#tj').removeAttr("disabled");
       }
    })
});