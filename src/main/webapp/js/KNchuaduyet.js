
$(document).ready(function(){	
	console.log("daload");
});

function openModal(maKN) { ;
    $('#maKNInputPH').val(maKN);
    // Mở modal
    $('#taophanhoiKN').modal('show');
};

function openHuyModal(maKN){
	$('#idKH').val(maKN);
    // Mở modal
    $('#xacnhanhuyKN').modal('show');

};

function openXemModal(maKN,date,content) { ;
    $('#idNguoigui').val(maKN);
    $('#idNgayGui').val(date);
    $('#content').val(content);
    
    // Mở modal
    $('#xemKN').modal('show');
};

function GuiPH() { 	
    		var maKNValue = document.getElementById('maKNInputPH').value;
    		var content = document.getElementById('contentPH').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "updatePH", maKNInput: maKNValue, noidungKNInput: content  },
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
			location.reload();
};

function XoaKN() { 
    		var maKNValue = document.getElementById('idKH').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/khieunai",
                data: { action: "deletekn", maKNInput: maKNValue },
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
			    data: { action: "listKNchuaD" },
			    success: function (data) {		
					location.reload();		
			    },
			    error: function () {
			        alert("Lỗi load khiếu nại.");
			    }
			});
			location.reload();
};






