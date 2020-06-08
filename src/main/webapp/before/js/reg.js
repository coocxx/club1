
	//验证用户名和密码格式是否正确
	function verifyInfo() {
        //昵称
        if(!verifyUserName(document.form1.name.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请按要求填写昵称(字母3-20位)!");
              });  
            return false;
        }
       //真实姓名
        if(!verifyName(document.form1.realname.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请按要求输入真实姓名(汉字和英文都可以)!");
              });  
            return false;
        }
        //密码
        if(!verifyPwd(document.form1.password.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请按照要求填写密码(字母或数字，6-20位)");
              });  
            return false;
        }
        
        //学号
        if (!verifySid(document.form1.sid.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请按要求填写学号(10位数字)");
              });  
            return false;
        }
        //电话号码
        if(!verifyTel(document.form1.phonenumber.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请按要求输入电话号码(11位数字)!");
              });  
            return false;
        }
        //电子邮箱
        if(!verifyEmail(document.form1.email.value)) {
        	 layui.use('layer', function(){
                 var layer = layui.layer;
                 layer.msg("电子邮箱格式不对");
               });  
            return false;
        }
        //验证码
        if (!verifyNotNull(document.form1.code.value)) {
        	 layui.use('layer', function(){
                 var layer = layui.layer;
                 layer.msg("验证码不能为空!");
               });  
            return false;
        }
    }

	
	
    