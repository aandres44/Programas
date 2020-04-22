import java.lang.reflect.Array;
import java.util.Arrays;

public class Dispersa {

  private double[] value, row, column;

  public Dispersa(double[] value, double[] row, double[] column) {
    this.value = value.clone();
    this.row = row.clone();
    this.column = column.clone();
  }

  public double encontrarValor(int i, int j) {
      
      int f = 0;
      boolean found = false;

      while(!found && f < row.length){
          if(row[f] == i){
              found = true;
          } else {
              f++;
          }
      }
      
      if (found && column[f] == j) {
          return value[f];
      } else {
          return 0;
      }
  
  }

  public int encontrarIndex(int i, int j) {
    int f = 0;
    boolean found = false;

    while(!found && f < row.length){
        if(row[f] == i){
            found = true;
        } else {
            f++;
        }
    }
    
    return f;
  }

  public void sustituirValor(int i, int j, double valor, double epsilon) {

    double oldValor = encontrarValor(i, j);

      /*Posibilidades: 
      1 que valor sea 0 y no este en los arreglos el valor anterior; no se hace nada
      2 valor sea 0 y haya un valor en los arreglos; se borra de los arreglos y se cambia el tamano creando nuevos arreglos y pasando todo
      3 valor es dif de 0 y valor anterior es 0; se cambian los arreglos y se agrega el valor en el orden correspondiente
      4 valor dif de 0 y valor anterior dif de 0; simplemente se cambia el valor correspondiente
      */
      
      /* make a one bigger
      value = Arrays.copyOf(value, value.length - 1);
      for (int i : a)
      System.out.println(i);*/

      //Caso 1: 0, 0
      if(Math.abs(valor) < epsilon && Math.abs(oldValor) < epsilon) {

      } else if(Math.abs(valor) < epsilon && Math.abs(oldValor) > epsilon) { //Caso 2: 0, #
          recorteEn(i,j);
      } else if(Math.abs(valor) > epsilon && Math.abs(oldValor) < epsilon) { //Caso 3: #, 0
          insertarEn(i, j, valor);
      } else { //Caso 4: #, #
          value[encontrarIndex(i, j)] = valor;
      }

      
  
  }

  public void insertarEn(int i, int j, double valor) {
    /*Va a buscar el index donde i == row[] o donde i < row y a partir de ahi seguira iterando hasta que column[] baje en lugar de subir
      porque en este momento se habra cambiado a la siguiente row
    */
    int f = 0;
    boolean found = false;
    boolean greater = false;

    while(!found && f < row.length && !greater){
        if(row[f] == i){
            found = true;
        } else if(i < row[f]) {
          greater = true;
          f--;
        } else {
            f++;
        }
    }

    if(greater) {//no hay un valor en la fila i, por lo tanto hay que hacer una insercion ahi

    } else if(found) {//hay un elemento en la fila i, por lo tanto hay que iterar en column como se indica al inicio del metodo
      found = false;
      boolean sameRow = true;

      /*
      while(!found && f < column.length - 1 && sameRow){
        if(column[f] > column[f + 1]) {
          sameRow = false;
        } else if (column[f + 1] == j) {

        } else {
            f++;
        }
      }*/

      while(sameRow && f < column.length && found == false) {
        if(row[f] != i) {
          sameRow = false;
          f--;
        } else if(j < column[f]) {
          found = true;
          f--;
        } else {
          f++;
        }
      }


    } else {//no hay elementos en la fila i, y ademas esta fila es la mas grande que entraria a los arreglos, esto se puede hacer en el primer if
      f++; //se aumenta el valor ya que f debe ser el valor donde se realice la insercion y en este caso es en el ultimo indice del nuevo arreglo mayor
    }
    
    value  = Arrays.copyOf(value , value.length  + 1);
    row    = Arrays.copyOf(row   , row.length    + 1);
    column = Arrays.copyOf(column, column.length + 1);

    for (int k = value.length - 1; k > f + 1; k--) {
      value [k - 1]  = value [k];
      row   [k - 1]  = row   [k];
      column[k - 1]  = column[k];
    }

    value[f]  = valor;
    row[f]    = i;
    column[f] = j;

  }

  public void recorteEn(int i, int j) {

    int index = encontrarIndex(i,j);

    value[index] = 0;

    for (int k = index; k < value.length - 1; k++) {
      value [k]  = value [k + 1];
      row   [k]  = row   [k + 1];
      column[k]  = column[k + 1];
    }

    value  = Arrays.copyOf(value , value.length  - 1);
    row    = Arrays.copyOf(row   , row.length    - 1);
    column = Arrays.copyOf(column, column.length - 1);

  }

  public void matrisDispersa(double epsilon) {

    int f = 0; //iterador de los arreglos

    int p = (int) row[row.length - 1];


    for (int j = 0; j <= p; j++) {
      System.out.print("\n");
      for (int i = 0; i <= p; i++) {
        if (column[f] == i && row[f] == j) {
          System.out.print((int) value[f++] + " ");
        } else {
          System.out.print(0  + " ");
        }
        if (f == column.length) {
          f--;
        }
      }
    }

    //Matrix abReduced = this.clone();
    for(int i = 0; i < p; i++)
    {
      if(Math.abs(encontrarValor(i, i)) < epsilon)
      {
        for(int k = i + 1; k < p; k++)
        {
          if(Math.abs(encontrarValor(k, i)) > epsilon)//k i
          {
            // Intercambiar renglones va aquí: renglón k con i
            for(int j = 0; j < p; j++)
            {
              double temp = encontrarValor(k, j);
              sustituirValor(k, j, encontrarValor(i, j), epsilon);
              sustituirValor(i, j, temp, epsilon);
            }
            break;
          }
        }
        if(Math.abs(encontrarValor(i, i)) < epsilon)
        {
          //// Sistema de ecuaciones es o indeterminado o inconsistente
        }
      }
      double piv = encontrarValor(i, i);
      for(int j = 0; j < p; j++)
      {
        sustituirValor(i, j, encontrarValor(i, j) / piv, epsilon);
      }
      // Hacer ceros toda la columna del pivote
      for(int k = 0; k < p; k++)
      {
        if(k != i)
        {
          double factor = -abReduced.data[k][i];
          for(int j = 0; j < n; j++)
          {
            abReduced.data[k][j] = abReduced.data[i][j] * factor + abReduced.data[k][j]; 
          }
        }
      }
    }
    return abReduced;
  }
}
