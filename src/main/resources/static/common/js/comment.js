function replyComment(name,id,content,type) {
    xtip.win({
        type: 'confirm', //alert 或 confirm
        btn: ['回复'],
        tip: '<div class="form-group" style="margin-bottom: 0px">\n' +
                '<textarea id="replyContent" name="message" class="form-control" placeholder="此处填写评论内容..."></textarea>\n' +
            '</div>',
        title: "回复"+name+"的评论："+content,
        min: true,
        width: '600px',
        shade: false,
        shadeClose: false,
        lock: false,
        btn1: function(){
            var reply = $('#replyContent').val().trim();
            if (reply != ""){
                $.ajax({
                    type: "POST",
                    url: "replyComment",
                    data: {id:id,content:reply,type:type},
                    dataType: "json",
                    success: function(data){
                        if (data['code']== 200){
                            xtip.alert('回复成功','s');
                            window.location.reload();
                        }else {
                            xtip.alert("服务器发生了一个错误",'e');
                        }
                    }
                });
            }
        },
        zindex: 99999,
    });
}

/**
 * 添加评论
 * @param id
 * @param type
 */
function addComment(id,type) {
    var content = $('#content').val().trim();
    if (content != ""){
        $.ajax({
            type: "POST",
            url: "addComment",
            data: {id:id,content:content,type:type},
            dataType: "json",
            success: function(data){
                if (data['code']== 200){
                    xtip.alert('评论成功','s');
                    window.location.reload();
                }else {
                    xtip.alert("服务器发生了一个错误",'e');
                }
            }
        });
    }else {
        xtip.alert('评论内容不能为空','w')
    }
}