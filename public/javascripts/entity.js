$(function() {
	$("#zaodian_save").bind("click",function(){
		/*	var note = $("#note").val();
			var action_url = $("#action_url").val();
			var icon = $("#icon").val();
			if(note == null || note == "")
			{
				info_trends("请输入封面说明!");
				
				$("#title").focus();
				return;
			}
			if(icon == null || icon == "")
			{
				info_trends("请选择图片!");
		  		
		  		return; 
			}
			//summent=summent.replace(/\n/g,'<br />');
			//summent = summent.replace(/ /g, '&nbsp;');
			var status = "";
			var ok=document.getElementsByName("status");
			for(i=0;i<ok.length;i++)
			{
				if (ok[i].checked == true){
					status = ok[i].value;
				}
			}*/
		
			var Paramlist = $('#zaodian_save_form').serialize();
			var url = "/zaodian_save";
			$.post(url,Paramlist,function(result){
				if(result != null)
				{
					if(result.result == "ok")
					{
						info_trends("封面更新成功!");
						
						window.setInterval('edit2page();',2000); 
					}else
					{
						info_trends("服务错误请刷新后进行操作!");
						
					}
				}
			});
		});
});
