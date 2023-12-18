package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.apache.poi.ss.usermodel.*;

import Models.HoSo;
import Models.NhanVien;

public class ExcelImporter {
	public static List<NhanVien> importFromExcelNV(String filePath) throws IOException {
		List<NhanVien> nhanVienList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}

	            boolean allCellsNull = true;
	            for (Cell cell : row) {
	                if (cell != null && cell.getCellType() != CellType.BLANK) {
	                    allCellsNull = false;
	                    break;
	                }
	            }

	            if (allCellsNull) {
	                break;
	            }
	            
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNV(row.getCell(0).getStringCellValue());
				nhanVien.setHoTen(row.getCell(1).getStringCellValue());
				nhanVien.setLuongCoBan((float) row.getCell(2).getNumericCellValue());
				nhanVien.setEmailCongViec(row.getCell(3).getStringCellValue());
				nhanVien.setTrangThai(row.getCell(4).getStringCellValue());
				nhanVien.setDuongDanAnh(row.getCell(5).getStringCellValue());
				
				nhanVienList.add(nhanVien);
			}
		}

		return nhanVienList;
	}
	

    public static List<HoSo> importFromExcelHoSo(String filePath) throws IOException {
        List<HoSo> hoSoList = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                boolean allCellsNull = true;
                for (Cell cell : row) {
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        allCellsNull = false;
                        break;
                    }
                }

                if (allCellsNull) {
                    break;
                }

                HoSo hoSo = new HoSo();
                hoSo.setMaHS(getStringCellValue(row.getCell(0)));
        		System.out.println(hoSo.getMaNV());
                hoSo.setMaNV(getStringCellValue(row.getCell(1)));
                hoSo.setcCCD(getStringCellValue(row.getCell(2)));
                hoSo.setNoiCapCCCD(getStringCellValue(row.getCell(3)));
                hoSo.setNgayCapCCCD(getLocalDateCellValue(row.getCell(4)));
                hoSo.setMaSoThue(getStringCellValue(row.getCell(5)));
                hoSo.setNgayCapMST(getLocalDateCellValue(row.getCell(6)));
                hoSo.setSoDienThoai(getStringCellValue(row.getCell(7)));
                hoSo.setGioiTinh(getBooleanCellValue(row.getCell(8)));
                hoSo.setQuocTich(getStringCellValue(row.getCell(9)));
                hoSo.setDanToc(getStringCellValue(row.getCell(10)));
                hoSo.setTonGiao(getStringCellValue(row.getCell(11)));
                hoSo.setNgaySinh(getLocalDateCellValue(row.getCell(12)));
                hoSo.setNoiSinh(getStringCellValue(row.getCell(13)));
                hoSo.setDiaChi(getStringCellValue(row.getCell(14)));
                hoSo.setTinhThanh(getStringCellValue(row.getCell(15)));
                hoSo.setQuanHuyen(getStringCellValue(row.getCell(16)));
                hoSo.setPhuongXa(getStringCellValue(row.getCell(17)));
                hoSo.setEmailCaNhan(getStringCellValue(row.getCell(18)));
                hoSo.setTinhTrangHN(getStringCellValue(row.getCell(19)));
                hoSo.setTrinhDoVanHoa(getStringCellValue(row.getCell(20)));
                hoSo.setTrinhDoHocVan(getStringCellValue(row.getCell(21)));
                hoSoList.add(hoSo);
            }   
        }
        return hoSoList;
    }

    private static boolean getBooleanCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue() == 1.0;
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                return cell.getBooleanCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                // Adjust this part based on the actual string values in your Excel sheet
                String stringValue = cell.getStringCellValue().trim();
                return "1".equals(stringValue);
            }
        }
        return false;
    }

    
	private static String getStringCellValue(Cell cell) {
	    return cell != null ? cell.getStringCellValue() : null;
	}
	
	private static LocalDate getLocalDateCellValue(Cell cell) {
	    if (cell == null || !DateUtil.isCellDateFormatted(cell)) {
	        return null;
	    }

	    return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
