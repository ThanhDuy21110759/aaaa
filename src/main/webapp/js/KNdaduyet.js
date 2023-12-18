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
			    data: { action: "listKNdaD" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};

function TaoKN1() { 
			console.log("cc");
    		var email1 = document.getElementById('email_send1').value;
   	 		var content1 = document.getElementById('content_send1').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "insertkn", email_send: email1, content_send:content1 },
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
			    data: { action: "listKNdaD" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};