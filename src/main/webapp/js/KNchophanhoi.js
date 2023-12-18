
/*lấy dữ liệu cho modal*/
function prepareModalData(maKN) { 
    
    var maKNValue = $('#' + maKN).text();
    var noidungKNValue = $('#' + maKN + '_noidung').text();
    // Đặt giá trị vào các trường input trong modal
    $('#maKNInput').val(maKNValue);
    $('#noidungKNInput').val(noidungKNValue); 
    // Mở modal
    $('#suaKhieunai').modal('show');
}



/*xóa khiếu nại*/
function XoaKN(maKN) { 
    
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "deletekn", maKNInput: maKN },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi xóa khiếu nại.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/khieunai",
			    data: { action: "listKNchoD" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
}
     
/*cập nhật khiếu nại*/
function SuaKN() { 
    		var maKNValue = document.getElementById('maKNInput').value;
   	 		var noidungKNValue = document.getElementById('noidungKNInput').value;
            console.log("cc");
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "editkn", maKNInput: maKNValue, noidungKNInput:noidungKNValue },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi sửa khiếu nại.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/khieunai",
			    data: { action: "listKNchoD" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};


function TaoKN() { 
			console.log("cc");
    		var email = document.getElementById('email_send').value;
   	 		var content = document.getElementById('content_send').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "insertkn", email_send: email, content_send:content },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi tạo khiếu nại.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/khieunai",
			    data: { action: "listKNchoD" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};