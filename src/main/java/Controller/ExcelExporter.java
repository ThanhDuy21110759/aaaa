package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Models.NhanVien;

public class ExcelExporter {
    public static void exportToExcel(List<NhanVien> nhanVienList, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("NhanVienData");
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Mã NV", "Họ Tên", "Lương Cơ Bản", "Email Công Việc", "Trạng Thái", "Đường Dẫn Ảnh"};

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        int rowNum = 1;
        for (NhanVien nhanVien : nhanVienList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(nhanVien.getMaNV());
            row.createCell(1).setCellValue(nhanVien.getHoTen());
            row.createCell(2).setCellValue(nhanVien.getLuongCoBan());
            row.createCell(3).setCellValue(nhanVien.getEmailCongViec());
            row.createCell(4).setCellValue(nhanVien.getTrangThai());
            row.createCell(5).setCellValue(nhanVien.getDuongDanAnh());
            
            System.out.println("Mã NV: " + nhanVien.getMaNV());
            System.out.println("Họ Tên: " + nhanVien.getHoTen());
            System.out.println("Lương Cơ Bản: " + nhanVien.getLuongCoBan());
            System.out.println("Email Công Việc: " + nhanVien.getEmailCongViec());
            System.out.println("Trạng Thái: " + nhanVien.getTrangThai());
            System.out.println("Đường Dẫn Ảnh: " + nhanVien.getDuongDanAnh());
            System.out.println();
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
    

}
