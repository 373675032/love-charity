/**
 * 添加链接
 */
function addLink() {
    let name = $("#name").val().trim();
    let url = $("#url").val().trim();
    let type = $('#type').find('option:selected').val();
    // 正则验证格式

    if (name == ''){
        alert("请输入昵称");
        return;
    }

    if (url == ''){
        alert("链接地址不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "addLink",
        data: {url:url,name:name,type:type},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("添加成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 删除链接
 * @param id
 */
function deleteLink(id) {
    $.ajax({
        type: "GET",
        url: "deleteLink",
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