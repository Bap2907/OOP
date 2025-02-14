package controller;

import model.connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class DanhMucChi {
    
     // lấy danh mục trong cơ sở dữ liệu để đưa ra jcombobox
    public static void populateDanhmucComboBox(JComboBox<String> Danhmuc) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCConnection.getJDBCConnection();
            String sql = "SELECT DanhMuc FROM DANHMUCTC";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Danhmuc.removeAllItems();

            while (resultSet.next()) {
                String danhMuc = resultSet.getString("DanhMuc");
                Danhmuc.addItem(danhMuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
     //Thêm mục 
    public static void themMucActionPerformed(JComboBox<String> Danhmuc) {
        String newCategory = JOptionPane.showInputDialog(Danhmuc, "Thêm danh mục mới:");
        if (newCategory != null && !newCategory.trim().isEmpty()) {
            if (!isCategoryAlreadyExists(newCategory, Danhmuc)) {
                Danhmuc.addItem(newCategory);
                Danhmuc.setSelectedItem(newCategory);
                saveCategoryToDatabase(newCategory,Danhmuc);
            } else {
                JOptionPane.showMessageDialog(Danhmuc, "Danh mục đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Danhmuc.setSelectedItem(" ");
        }
    }
    
    // lưu danh mục khi thêm vào cơ sở dữ liệu
    private static void saveCategoryToDatabase(String newCategory, JComboBox<String> Danhmuc) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO DANHMUCTC (DanhMuc) VALUES (?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newCategory);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(Danhmuc, "Danh mục đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Danhmuc, "Lỗi khi thêm danh mục", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {      
        e.printStackTrace();
    } finally {  
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

     // Ràng buộc kiểm tra khi thêm và sửa danh mục có trùng trong cơ sở dữ liêu hay không
    private static boolean isCategoryAlreadyExists(String newCategory, JComboBox<String> Danhmuc) {
        String[] categories = getCategories(Danhmuc);
        for (String category : categories) {
            if (category.equalsIgnoreCase(newCategory.trim())) {
                return true; 
            }
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCConnection.getJDBCConnection();
            String sql = "SELECT COUNT(*) FROM DANHMUCTC WHERE UPPER(DanhMuc) = UPPER(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newCategory.trim());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
    // Phương thức để lấy danh sách các danh mục từ JComboBox
    private static String[] getCategories(JComboBox<String> Danhmuc) {
        int itemCount = Danhmuc.getItemCount();
        String[] categories = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            categories[i] = (String) Danhmuc.getItemAt(i);
        }
        return categories;
    }

   
    
    
     // Xóa mục
    public static void XoaMucActionPerformed(JComboBox<String> Danhmuc) {                                       
            
    String[] categories = getCategories(Danhmuc);
    String selectedCategory = (String) JOptionPane.showInputDialog(
            Danhmuc,
            "Chọn danh mục để xóa:",
            "Xóa mục",
            JOptionPane.QUESTION_MESSAGE,
            null,
            categories,
            categories.length > 0 ? categories[0] : null
    );
    if (selectedCategory != null) {
        Danhmuc.removeItem(selectedCategory);
        deleteCategoryFromDatabase(selectedCategory,Danhmuc );
    }
    }                                      

                                
    // hàm để xóa danh mục khỏi cơ sở dữ liệu
    private static void deleteCategoryFromDatabase(String selectedCategory,JComboBox<String> Danhmuc) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM DANHMUCTC WHERE DanhMuc=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, selectedCategory);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(Danhmuc, "Danh mục đã được xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Danhmuc, "Lỗi khi xóa danh mục", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    //Sửa mục
    public static void SuaMucActionPerformed(JComboBox<String> Danhmuc) {                                       

    String selectedCategory = (String) Danhmuc.getSelectedItem();
    if (selectedCategory == null || selectedCategory.trim().isEmpty()) {
        JOptionPane.showMessageDialog(Danhmuc, "Vui lòng chọn một danh mục để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String newCategory = JOptionPane.showInputDialog(Danhmuc, "Nhập tên danh mục mới:", selectedCategory);
    if (newCategory != null && !newCategory.trim().isEmpty()) {
        if (!isCategoryAlreadyExists(newCategory,Danhmuc)) {
            Danhmuc.removeItem(selectedCategory);
            Danhmuc.addItem(newCategory);
            Danhmuc.setSelectedItem(newCategory);
            updateCategoryInDatabase(selectedCategory, newCategory,Danhmuc);
        } else{
            JOptionPane.showMessageDialog(Danhmuc, "Danh mục đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        Danhmuc.setSelectedItem(" "); 
        }
    }                                      
    
    // Cập nhật lại cơ sở dữ liệu khi sửa mục
    private static void updateCategoryInDatabase(String oldCategory, String newCategory,JComboBox<String> Danhmuc) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE DANHMUCTC SET DanhMuc=? WHERE DanhMuc=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newCategory);
        preparedStatement.setString(2, oldCategory);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(Danhmuc, "Danh mục đã được cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Danhmuc, "Lỗi khi cập nhật danh mục", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng tài nguyên
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
  
    
    
    
    
    
}
