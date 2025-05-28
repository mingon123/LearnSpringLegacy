$(function(){
	/*============================
	 MY페이지 프로필 사진 등록 및 수정
	=============================*/
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide();
	});
	
	// 처음 화면에 보여지는 이미지 읽기
	let photo_path = $('.my-photo').attr('src');
	$('#upload').change(function(){
		let my_photo = this.files[0];
		if(!my_photo){
			// 선택을 취소하면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src',photo_path);
			return;
		}
		
		if(my_photo.size > 1024 * 1024){
			alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbyte까지만 업로드 가능)');
			$('.my-photo').attr('src',photo_path);
			$(this).val(''); // 선택한 파일 정보 지우기
			return;
		}
		
		// 화면에서 이미지 미리보기
		const reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result);
		};
	}); // end of change
	
	// 파일 전송 처리
	$('#photo_submit').click(function(){
		if($('#upload').val()==''){
			alert('파일을 선택하세요!');
			$('#upload').focus();
			return
		}
		// 서버와 통신
		let form_data = new FormData();
		form_data.append('upload',$('#upload')[0].files[0]); // 배열로 처리하기 때문에 0번인덱스에서 읽어옴
		$.ajax({
			url:'updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false, // 파일업로드인 경우 이하 2개 false 추가해야 함
			processData:false,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					// 수정된 이미지 정보 저장
					photo_path = $('.my-photo').attr('src');
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show(); // 수정 버튼 표시
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		}); 
	}); // end of click
	
	// 파일 업로드 취소
	$('#photo_reset').click(function(){
		// 이미지 미리보기 전 이미지로 되돌리기
		$('.my-photo').attr('src',photo_path);
		$('#upload').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();
	});
	
});