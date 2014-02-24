var tmbb=function(id) {
   return document.getElementById(id);
}

function show_menuB(numB){
	for(j=0;j<100;j++){
		 if(j!=numB){
			if(tmbb('Bli0'+j)){
		  		tmbb('Bli0'+j).style.display='none';
		  		tmbb('Bf0'+j).style.background='url(/template/admin/imgs/index_16.jpg)';
			}
		 }
	}
	if(tmbb('Bli0'+numB)){ 
		if(tmbb('Bli0'+numB).style.display=='block'){
			tmbb('Bli0'+numB).style.display='none';
		 	tmbb('Bf0'+numB).style.background='url(/template/admin/imgs/index_16.jpg)';
		}else {
			tmbb('Bli0'+numB).style.display='block';
			tmbb('Bf0'+numB).style.background='url(/template/admin/imgs/index_15.jpg)';
		}
	}
}

function show_menu(num){
	for(i=0;i<100;i++){
		if(tmbb('f0'+i))
			if(i!=num){
				tmbb('f0'+i).className='left02down01_xiali';
			}
	}
	tmbb('f0'+num).className='left02down01_xialilink';
}