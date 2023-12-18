function moModalsuaCN(maCN,tenCN,maGD,trangthai) { 
    
    $('#machinhanh_input2').val(maCN);
    $('#tenchinhanh_input2').val(tenCN); 
    $('#maGD_input2').val(maGD); 
    $('#trangthai').val(trangthai); 
    // Mở modal
    $('#suaCN').modal('show');
};

function TaoCN() { 
    		var macn = document.getElementById('machinhanh_input1').value;
   	 		var tencn = document.getElementById('tenchinhanh_input1').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/chinhanh",
                data: { action: "taoCN", machinhanh_input: macn, tenchinhanh_input:tencn },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/chinhanh",
			    data: { action: "listChinhanh" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};
function SuaCN() { 
	
    		var macn = document.getElementById('machinhanh_input2').value;
   	 		var maGD = document.getElementById('maGD_input2').value;
   	 		var tenCN = document.getElementById('tenchinhanh_input2').value;
   	 		var tt = document.getElementById('trangthai').value;
   	 		
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/chinhanh",
                data: { action: "suaCN", machinhanh_input2: macn, maGD_input2:maGD,tenchinhanh_input2:tenCN,trangthai:tt },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi .");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/chinhanh",
			    data: { action: "listChinhanh" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};
function XoaCN(){ 
    		var macn = document.getElementById('macn').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/chinhanh",
                data: { action: "xoaCN", machinhanh: macn },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi .");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/chinhanh",
			    data: { action: "listChinhanh" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};

function capnhatQuyenCN(){ 
    		var macn = document.getElementById('machinhanh_input3').value;
    		var manv = document.getElementById('maGD_input3').value;
    		console.log(macn);
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/chinhanh",
                data: { action: "capnhatGDCN", maNV: manv ,maCN: macn },
                success: function (data) {   
					console.log("hay");            
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/chinhanh",
			    data: { action: "listChinhanh" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};

function openHuyModalXoaCN(maCN){
	$('#macn').val(maCN);
    // Mở modal
    $('#xoaCN').modal('show');

};

function openHuyModalXoaPB(maPB){
	$('#mapb').val(maPB);
    // Mở modal
    $('#xoaPB').modal('show');

};

function openModalcapnhatQuyenCN(maCN,tenCN){
	$('#machinhanh_input3').val(maCN);
	$('#tenchinhanh_input3').val(tenCN);
	
    // Mở modal
    $('#doiGDCN').modal('show');

};

function openModalcapnhatQuyenPB(maPB,tenPB){
	$('#maphongban_input3').val(maPB);
	$('#tenphongban_input3').val(tenPB);
	
    // Mở modal
    $('#doiGDPB').modal('show');

};

function moModalsuaPB(maPB,tenPB,maGD,trangthai) { 
    
    $('#maphongban_input2').val(maPB);
    $('#tenphongban_input2').val(tenPB); 
    $('#maGD_input2').val(maGD); 
    $('#trangthai').val(trangthai); 
    // Mở modal
    $('#suaPB').modal('show');
};



function TaoPB() { 
    		var mapb = document.getElementById('maphongban_input1').value;
   	 		var tenpb = document.getElementById('tenphongban_input1').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/phongban",
                data: { action: "taoPB", maphongban_input1: mapb, tenphongban_input1:tenpb },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/phongban",
			    data: { action: "listPhongBan" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};
function SuaPB() { 
	
    		var macn = document.getElementById('maphongban_input2').value;
   	 		var maGD = document.getElementById('maGD_input2').value;
   	 		var tenCN = document.getElementById('tenphongban_input2').value;
   	 		var tt = document.getElementById('trangthai').value;
   	 		
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/phongban",
                data: { action: "suaPB", machinhanh_input2: macn, maGD_input2:maGD,tenchinhanh_input2:tenCN,trangthai:tt },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi .");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/phongban",
			    data: { action: "listPhongBan" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};
function XoaPB(){ 
    		var macn = document.getElementById('mapb').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/phongban",
                data: { action: "xoaPB", machinhanh: macn },
                success: function (data) {               
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi .");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/phongban",
			    data: { action: "listPhongBan" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};

function capnhatQuyenPB(){ 
    		var mapb = document.getElementById('maphongban_input3').value;
    		var manv = document.getElementById('maGD_input3').value;
            $.ajax({
                type: "POST",
                url: "/QuanLyNhanSu/phongban",
                data: { action: "capnhatGDPB", maNV: manv ,maPB: mapb },
                success: function (data) {   
					console.log("hay");            
					location.reload();		   	         
                },
                error: function () {
                    alert("Lỗi");
                }
            });
            $.ajax({
			    type: "POST",
			    url: "/QuanLyNhanSu/phongban",
			    data: { action: "listPhongBan" },
			    success: function (data) {
				
					location.reload();
			    },
			    error: function () {
			        alert("Lỗi .");
			    }
			});
};