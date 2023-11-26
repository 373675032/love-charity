/**
 * 留言
 */
function publishMessage() {
    let name = $("#name").val().trim();
    let email = $("#email").val().trim();
    let content = $("#content").val().trim();
    // 正则验证格式
    var emailReg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;

    if (name == ''){
        alert("请输入昵称");
        return;
    }
    if(!emailReg.test(email)){
        alert("请输入正确的邮箱格式");
        return;
    }
    if (content == ''){
        alert("请输入留言内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "publishMessage",
        data: {email:email,name:name,content:content},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("留言成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 删除留言
 * @param id
 */
function deleteBoard(id) {
    $.ajax({
        type: "GET",
        url: "deleteBoard",
        data: {id:id},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("删除成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}