var icons=[];
$(function () {
	 $("#upload_f_").bind('change', function () {
	    //fileChange(this);
		//$("#tag").val("1");
	    //$("#file_name_js_").html("上传中...");
	  //  $("#ifa").attr("src","/account/iphone_img_upload");
		
	    $('#upload_img_').submit();
	    $("#upload_f_").attr("disabled",false);
	    //window.setInterval("$('#upload_f_').attr('disabled',false);$('#file_name_js_').html('选择文件')",8000);
    }); 

});
function success_callback(v)
{
	var obj = $.parseJSON(v);   
	for ( var int = 0; int < obj.length; int++) {
		var name=obj[int];
		var url=host+"pic/"+name;
		icons.push(url);
		var jsonstring = JSON.stringify(icons);
		$("#icon").val(jsonstring);
		//alert(url);
		var item='<img alt="" src="'+url+'" width="100px" height="84px" style="margin-top: 15px;margin-right: 10px;">';
		$('#img_pre').after(item);
	}
	//   	
}   
