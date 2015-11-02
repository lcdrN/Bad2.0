package Table;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class JTableJoueur extends JTable{
	
    public JTableJoueur(AbstractTableModel table) {
      super(table);
  }

  Color alternate = new Color(255, 255, 255);
  Color alternate2 = new Color(240, 245, 255);

  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
      Component stamp = super.prepareRenderer(renderer, row, column);
      if (row % 2 == 0)
          stamp.setBackground(alternate);
      else
    	  stamp.setBackground(alternate2);
      return stamp;
  }
}