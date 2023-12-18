$(document).ready(function() {
	$('#thongtincoban-form').validate({
		rules: {
			"inputMaNV": {
				required: true,
			},
			"inputHoTen": {
				required: true,
			},
			"inputLuongCoBan": {
				required: true,
				number: true
			},
			"inputEmailCV": {
				required: true,
				email: true
			},
		},
		messages: {
			"inputMaNV": {
				required: 'Vui lòng nhập địa Mã Nhân Viên',
			},
			"inputHoTen": {
				required: 'Vui lòng nhập họ tên'
			},
			"inputLuongCoBan": {
				required: 'Vui lòng nhập lương cơ bản',
				number: 'Vui lòng nhập số'
			},
			"inputEmailCV": {
				required: 'Vui lòng nhập địa chỉ email',
				email: 'Vui lòng nhập địa chỉ email hợp lệ'
			},
		}
	});
});

$(document).ready(function() {
	$('#hoso-form').validate({
		rules: {
			"inputSDT": {
				required: true,
			},
			"inputNoiSinh": {
				required: true,
			},
			"inputCCCD": {
				required: true,
				// Add any specific rules for inputCCCD
			},
			"inputDiaChi": {
				required: true,
				// Add any specific rules for inputDiaChi
			},
			"inputNoiCapCCCD": {
				required: true,
				// Add any specific rules for inputNoiCapCCCD
			},
			"inputNgayCapCCCD": {
				required: true,
				// Add any specific rules for inputNgayCapCCCD
			},
			"inputEmailCN": {
				required: true,
				email: true, // You can add an email validation rule
			},
		},
		messages: {
			"inputHoTen": {
				required: 'Vui lòng nhập Số điện thoại',
			},
			"inputSDT": {
				required: 'Vui lòng nhập Số điện thoại',
			},
			"inputNoiSinh": {
				required: 'Vui lòng nhập nơi sinh',
			},
			"inputCCCD": {
				required: 'Vui lòng nhập số CCCD',
				// Add any specific messages for inputCCCD
			},
			"inputDiaChi": {
				required: 'Vui lòng nhập địa chỉ',
				// Add any specific messages for inputDiaChi
			},
			"inputNoiCapCCCD": {
				required: 'Vui lòng nhập nơi cấp CCCD',
				// Add any specific messages for inputNoiCapCCCD
			},
			"inputNgayCapCCCD": {
				required: 'Vui lòng nhập ngày cấp CCCD',
				// Add any specific messages for inputNgayCapCCCD
			},
			"inputEmailCN": {
				required: 'Vui lòng nhập địa chỉ email',
				email: 'Vui lòng nhập địa chỉ email hợp lệ',
			},
		}
	});
});

