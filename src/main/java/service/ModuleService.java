package service;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ModuleDao;
import model.MarkItem;
import model.Module;

public class ModuleService {
    private ModuleDao moduleDao = new ModuleDao();

    public void saveModule(Module module) {
        moduleDao.save(module);
    }

    public Module getModuleById(String id) {
        return moduleDao.getModuleById(id);
    }

    public void deleteModuleById(String id) {
        moduleDao.deleteModuleById(id);
    }

    public void updateModuleById(Module moduleUpdated) {
        moduleDao.updateModuleById(moduleUpdated);
    }

    public void addMarkItem(String id, MarkItem markItem) {
        moduleDao.addMarkItem(id, markItem);
    }

    public void updateMarkItem(String module_id, String mark_name, MarkItem markItem) {
        moduleDao.updateMarkItem(module_id, mark_name, markItem);
    }

    public double calculateGPA() {
        List<Module> modules = moduleDao.getAllModules();
        double totalCredit = 0;
        double totalMark = 0;
        for (Module module : modules) {
            totalCredit += module.getCredit();
            totalMark += module.getCredit() * module.getMark().calculateMark();
        }
        return totalMark / totalCredit;
    }

    public List<Module> getAllModules() {
        return moduleDao.getAllModules();
    }

    public void exportModuleInfo(String path) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Paragraph title = new Paragraph("Module Information");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); 

            PdfPTable table = new PdfPTable(3);
            table.addCell("Module Name");
            table.addCell("Module Credit");
            table.addCell("Module Mark");
            List<Module> modules = moduleDao.getAllModules();

            for (Module module : modules) {
                table.addCell(module.getName());
                table.addCell(String.valueOf(module.getCredit()));
                table.addCell(String.valueOf(module.getMark().calculateMark()));
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error creating pdf file");
        }
    }
}
