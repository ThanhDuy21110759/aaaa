$(document).ready(function(){
	console.log("cc");
});

function TaoKT() { 
   	 		var content = document.getElementById('content_send1').value;
   	 		var mangNhan1 = document.getElementById('maNgnhan1').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/ktkl",
                data: { action: "insertKTKL", content_send: content,maNgnhan: mangNhan1, check:"1" },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi tạo khen thưởng.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/ktkl",
			    data: { action: "listKTKL_trangthai" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};

function TaoKL() { 
   	 		var content = document.getElementById('content_send2').value;
   	 		var maNgnhan2 = document.getElementById('maNgnhan2').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/ktkl",
                data: { action: "insertKTKL", content_send: content,maNgnhan: maNgnhan2, check:"0" },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi tạo khen thưởng.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/ktkl",
			    data: { action: "listKTKL_trangthai" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};

function moModalDuyet(maQD,maNgnhan,noiDung) { 
   
    // Đặt giá trị vào các trường input trong modal
    $('#maQD_input').val(maQD);
    $('#maNgnhan_input').val(maNgnhan);
    $('#content_text').val(noiDung); 
    // Mở modal
    $('#duyetKTKL').modal('show');
}

function DuyetKTKL() { 
   	 		var maQD = document.getElementById('maQD_input').value;
   	 		var soQD = document.getElementById('soquyetdinh_input').value;
   	 		
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/ktkl",
                data: { action: "duyetDonKT", maQD_input: maQD ,soquyetdinh_input: soQD },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi tạo khen thưởng.");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/ktkl",
			    data: { action: "listKTKL_duyet" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
};


