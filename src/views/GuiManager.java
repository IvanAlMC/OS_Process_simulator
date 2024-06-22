package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GuiManager extends JFrame {
    int count;//count del total de procesos que se van ingresando
    int numberProcess;//Carga el número de procesos ejecutándose
    int initialTimeProcess = 0;//Carga la ráfaga en ejecución
    int Quantum = 0;//Carga el quantum en ejecución
    int leftTimeProcess = 0;//Carga el residue en ejecución
    int timeProcess = 0;//Carga el tiempo que se dura procesando
    int ProgressBarValue;//Carga el progreso de la updateDataProgressBar
    int quantityProcess;//Número de procesos terminados
    String nameProcess = null;
    long timeWait = 0;//Tiempo de espera

    boolean isBlocked = false;//Indica si el proceso está bloqueado
    boolean isWaiting = false;//Indica si el proceso está en espera

    Map<Integer, Integer> state = new HashMap<>();//Guarda el estado de un proceso
    // (0 = Listo, 1 = Bloqueado, 2 = Bloqueado terminado )


    // Variables declaration - do not modify
    private JButton JBtnAccept;
    private JButton jBtnStart;
    private JLabel JLProgressBar;
    private JLabel jLabelActualProcess;
    private JLabel jLabelHistory;
    private JLabel jLabelInsertName;
    private JLabel jLabelInsertQuantum;
    private JLabel jLabelInsertTime;
    private JLabel jLabelPercentage;
    private JLabel jLabelProcessList;
    private JLabel jLabelProcessNumber;
    private JLabel jLabelQuantityProcess;
    private JLabel jLabelTerminatedProcess;
    private JLabel jLabelTotalTime;
    private JProgressBar jPActualProcess;
    private JTextField jTextGetProcessName;
    private JTextField jTextGetProcessTime;
    private JTextField jTextGetQuantum;
    private JTextField jTextNumberActualProcess;
    private JTextField jTextProgressBarPercentage;
    private JTextField jTextQuantityProcess;
    private JTextField jTextTotalTime;
    private JTable jTableProcessList;
    private JTable jTableTerminatedProcess;

    private JCheckBox jCheckBox2;
    private JLabel jLabel13;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTable jTable1;
    private JTable jTable2;

    /**
     * Creates new form Procesar
     */
    public GuiManager() {
        super(Texts.textTittle);
        this.setLookAndFeel();
        initComponents();

        jTableProcessList.setBackground(Constants.colorListProcessList);

        jTableProcessList.setForeground(Constants.foregroundTableProcessList);
        jTableTerminatedProcess.setBackground(Color.GREEN);
        jTextGetQuantum.grabFocus();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /***
     * Cambia la apariencia de la interfaz de usuario segun el sistema opetativo en el que se ejecute
     */
    private void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /***
     * Crea los componentes de la vista
     */
    private void initComponents() {

        jScrollPane2 = new JScrollPane();
        jTable1 = new JTable();
        jScrollPane1 = new JScrollPane();
        jTable2 = new JTable();
        jLabelInsertQuantum = new JLabel();
        JBtnAccept = new JButton();
        jScrollPane3 = new JScrollPane();
        jTableProcessList = new JTable();
        jTextGetQuantum = new JTextField();
        jLabelInsertTime = new JLabel();
        jTextGetProcessTime = new JTextField();
        jLabelProcessList = new JLabel();
        jPActualProcess = new JProgressBar();
        jBtnStart = new JButton();
        jLabelHistory = new JLabel();
        jTextNumberActualProcess = new JTextField();
        jTextProgressBarPercentage = new JTextField();
        jLabelQuantityProcess = new JLabel();
        jLabelTotalTime = new JLabel();
        jTextQuantityProcess = new JTextField();
        jTextTotalTime = new JTextField();
        jScrollPane4 = new JScrollPane();
        jTableTerminatedProcess = new JTable();
        jLabelActualProcess = new JLabel();
        jLabelInsertName = new JLabel();
        jTextGetProcessName = new JTextField();
        jLabelTerminatedProcess = new JLabel();
        jLabelProcessNumber = new JLabel();
        JLProgressBar = new JLabel();
        jLabelPercentage = new JLabel();
        jLabel13 = new JLabel();
        jCheckBox2 = new JCheckBox();

        jTable1.setModel(Constants.defaultTableModel);
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(Constants.defaultTableModel);
        jScrollPane1.setViewportView(jTable2);


        jLabelInsertQuantum.setFont(Constants.TahomaPlain14); // NOI18N
        jLabelInsertQuantum.setText(Texts.labelInsertQuantum);


        JBtnAccept.setText(Texts.labelAddCPU);
        JBtnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JBtnAccept.addActionListener(evt -> btnAcceptActionPerformed(evt));


        jTableProcessList.setModel(Constants.dataModelListProcessList);
        jScrollPane3.setViewportView(jTableProcessList);


        jTextGetQuantum.setFont(Constants.tahoma12); // NOI18N
        jTextGetQuantum.addActionListener(this::JTGetQuantumActionPerformed);
        jTextGetQuantum.addKeyListener(Constants.validateOnlyNumber);

        jLabelInsertTime.setFont(Constants.TahomaPlain14); // NOI18N
        jLabelInsertTime.setText(Texts.labelInsertTime);

        jTextGetProcessTime.setFont(Constants.tahoma12); // NOI18N
        jTextGetProcessTime.addActionListener(this::JTFgetProcessTimeActionPerformed);

        jTextGetProcessTime.addKeyListener(Constants.validateOnlyNumber);
        jLabelProcessList.setFont(Constants.tahoma18); // NOI18N

        jLabelProcessList.setText(Texts.labelListProcess);

        jPActualProcess.setBackground(SystemColor.textHighlight);
        jPActualProcess.setFont(Constants.TahomaPlain14); // NOI18N
        jPActualProcess.setForeground(Constants.colorProcessActual);
        jPActualProcess.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(102, 102, 255)));

        jBtnStart.setText(Texts.textBtnInit);
        jBtnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jBtnStart.addActionListener(this::JStartActionPerformed);

        jLabelHistory.setFont(Constants.tahoma18); // NOI18N

        jLabelHistory.setText(Texts.labelHistory);

        jTextNumberActualProcess.setFont(Constants.tahoma12); // NOI18N
        jTextNumberActualProcess.addActionListener(this::JTFnumerActualProcessActionPerformed);
        jTextNumberActualProcess.setEnabled(false);
        jTextNumberActualProcess.setEditable(false);


        jTextProgressBarPercentage.setFont(Constants.tahoma12); // NOI18N
        jTextProgressBarPercentage.addActionListener(this::JTFprogressBarPercentageActionPerformed);
        jTextProgressBarPercentage.setEnabled(false);
        jTextProgressBarPercentage.setEditable(false);

        jLabelQuantityProcess.setFont(Constants.tahoma12); // NOI18N

        jLabelQuantityProcess.setText(Texts.labelQuantityProcess);

        jLabelTotalTime.setFont(Constants.tahoma12); // NOI18N

        jLabelTotalTime.setText(Texts.labelTimeTotal);

        jTextQuantityProcess.setFont(Constants.tahoma12); // NOI18N
        jTextQuantityProcess.addActionListener(this::JTQuantityProcessActionPerformed);
        jTextQuantityProcess.setEnabled(false);
        jTextQuantityProcess.setEditable(false);


        jTextTotalTime.setFont(Constants.tahoma12); // NOI18N
        jTextTotalTime.addActionListener(this::JTFtotalTimeActionPerformed);
        jTextTotalTime.setEnabled(false);
        jTextTotalTime.setEditable(false);


        jTableTerminatedProcess.setModel(Constants.tableProcessTerminates);
        jScrollPane4.setViewportView(jTableTerminatedProcess);

        jLabelActualProcess.setFont(Constants.tahoma18); // NOI18N

        jLabelActualProcess.setText(Texts.labelNameProcessActual);

        jLabelInsertName.setFont(Constants.TahomaPlain14); // NOI18N

        jLabelInsertName.setText(Texts.labelGetNameNewProcess);

        jTextGetProcessName.addActionListener(this::JTFgetProcessNameActionPerformed);

        jLabelTerminatedProcess.setFont(Constants.tahoma18); // NOI18N

        jLabelTerminatedProcess.setText(Texts.labelListProcessTerminates);

        jLabelProcessNumber.setFont(Constants.TahomaPlain14); // NOI18N

        jLabelProcessNumber.setText(Texts.labelNumberProcess);

        JLProgressBar.setFont(Constants.TahomaPlain14); // NOI18N

        JLProgressBar.setText(Texts.labelProgressProcess);

        jLabelPercentage.setFont(Constants.TahomaPlain14); // NOI18N

        jLabelPercentage.setText(Texts.percentage);

        jLabel13.setFont(Constants.tahoma18); // NOI18N
        //        jLabel13.setText(Texts.textTittle);

        jCheckBox2.setText("Proceso con I/O");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                                        layout.createSequentialGroup().addGap(104, 104, 104).addGroup(
                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextGetProcessName, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jLabelInsertName, GroupLayout.Alignment.LEADING,
                                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabelInsertTime, GroupLayout.Alignment.LEADING,
                                                                        GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTextGetQuantum, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextGetProcessTime, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelInsertQuantum)
                                                        .addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jCheckBox2)))).addGroup(
                                        layout.createSequentialGroup().addGap(35, 35, 35).addGroup(
                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelHistory, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup().addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabelQuantityProcess, GroupLayout.PREFERRED_SIZE, 141,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabelTotalTime, GroupLayout.PREFERRED_SIZE, 141,
                                                                                GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextQuantityProcess, GroupLayout.PREFERRED_SIZE, 118,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextTotalTime, GroupLayout.PREFERRED_SIZE, 118,
                                                                                GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jLabelActualProcess, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelProcessNumber).addComponent(JLProgressBar)
                                                        .addComponent(jTextNumberActualProcess, GroupLayout.PREFERRED_SIZE, 53,
                                                                GroupLayout.PREFERRED_SIZE).addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPActualProcess,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        215,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextProgressBarPercentage,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        53,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabelPercentage))))
                                .addGroup(layout.createSequentialGroup().addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(
                                                        GroupLayout.Alignment.LEADING).addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(JBtnAccept,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        96,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(jBtnStart,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        89,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel13,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        257,
                                                        GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE).addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING,
                                                                                false).addComponent(
                                                                                jScrollPane3,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                708, Short.MAX_VALUE)
                                                                        .addComponent(
                                                                                jScrollPane4))
                                                        .addGap(22, 22, 22))
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup().addComponent(jLabelProcessList).addGap(267, 267, 267))
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup().addComponent(jLabelTerminatedProcess)
                                                        .addGap(233, 233, 233)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(68, 68, 68)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)).addGroup(layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                                        layout.createSequentialGroup().addGap(21, 21, 21)
                                                .addComponent(jLabelProcessList, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel13).addGap(13, 13, 13)
                                        .addComponent(jLabelInsertQuantum).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextGetQuantum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelInsertTime)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextGetProcessTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelInsertName)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextGetProcessName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2).addGap(17, 17, 17).addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(JBtnAccept, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnStart, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18).addComponent(jLabelActualProcess)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabelProcessNumber)
                                .addComponent(jLabelTerminatedProcess)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNumberActualProcess, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20).addComponent(JLProgressBar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPActualProcess, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPercentage, GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextProgressBarPercentage, GroupLayout.Alignment.TRAILING)).addGap(38, 38, 38)
                .addComponent(jLabelHistory, GroupLayout.PREFERRED_SIZE, 24,
                        GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelQuantityProcess, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextQuantityProcess, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextTotalTime,
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTotalTime,
                                GroupLayout.PREFERRED_SIZE, 20,
                                GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE)));

        pack();
    }

    /***
     * metodo que se ejecuta cuando se da click sobre el boton aceptar en el
     * panel de procesos
     * Inserta los datos en los campos del proceso dentro de la tabla de procesos listos
     * @param evt
     */
    private void btnAcceptActionPerformed(ActionEvent evt) {
        if ((Integer.parseInt(jTextGetProcessTime.getText())) <= 100) {
            insertData();
            jTextGetQuantum.setEditable(false);
            jCheckBox2.setSelected(false);
            jTextGetQuantum.setEnabled(false);
        } else {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, Texts.messageMaxTimeProcess);
            jTextGetProcessTime.setText(null);
            jTextGetProcessTime.grabFocus();
        }
    }

    private void JTFgetProcessTimeActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /***
     * Accion del boton de iniciar simulacion
     * inicia el hilo del proceso
     * @param evt
     */
    private void JStartActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        new Thread(new myThread()).start();
        startProcess();
    }

    private void JTGetQuantumActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void JTQuantityProcessActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void JTFtotalTimeActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {
        if (jTextGetProcessTime.getText().length() >= 10) {
            evt.consume();
        }
    }

    private void JTFnumerActualProcessActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void JTFprogressBarPercentageActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void JTFgetProcessNameActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /***
     * Duereme el hilo del proceso
     */
    public void sleepMethod() {
        try {
            Thread.sleep(1000); //sleepMethod sistema
        } catch (InterruptedException ex) {
            Logger.getLogger(GuiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /***
     * Carga los valores de la tablas
     * @param i indice de insercion
     */
    public void chargeData(int i) { //Carga los valuees de la Tabla
        numberProcess = (int) jTableProcessList.getValueAt(i, 0);
        nameProcess = (String) (jTableProcessList.getValueAt(i, 1));
        initialTimeProcess = Integer.parseInt((String) (jTableProcessList.getValueAt(i, 2)));
        Quantum = Integer.parseInt((String) (jTableProcessList.getValueAt(i, 3)));
        leftTimeProcess = Integer.parseInt((String) (jTableProcessList.getValueAt(i, 4)));
        isBlocked = (boolean) (jTableProcessList.getValueAt(i, 6));
        if (numberProcess > 0) jTextNumberActualProcess.setText(String.valueOf(numberProcess));
        if (isBlocked) timeWait = (long) jTableProcessList.getValueAt(i, 7);
        else timeWait = -1;
        isWaiting = jTableProcessList.getValueAt(i, 5).equals("Bloqueado");
        if (!state.containsKey(i)) state.put(i, 0);
    }

    public void insertData() { //insertData proceso a la tabla
        DefaultTableModel firstModel = (DefaultTableModel) jTableProcessList.getModel();
        count++;
        Object[] myTable = new Object[8];
        myTable[0] = count;
        myTable[1] = jTextGetProcessName.getText();
        myTable[2] = jTextGetProcessTime.getText();
        myTable[3] = jTextGetQuantum.getText();
        myTable[4] = jTextGetProcessTime.getText();
        myTable[5] = "Listo";
        myTable[6] = jCheckBox2.isSelected();
        myTable[7] = jCheckBox2.isSelected() ? (long) new Random().nextInt(
                Integer.parseInt(jTextGetProcessTime.getText()) - 1) + 1 : -1;
        firstModel.addRow(myTable);
        jTableProcessList.setModel(firstModel);
        jTextGetProcessTime.setText(null);
        jTextGetProcessTime.grabFocus();
        jTextGetProcessName.setText(null);
        jTextGetProcessName.grabFocus();
    }


    /***
     * Muestra el reporte en las tablas de procesos
     * @param c
     */
    public void showReport(int c) {
        DefaultTableModel secondModel = (DefaultTableModel) jTableTerminatedProcess.getModel();
        Object[] myTable = new Object[6];
        myTable[0] = c + 1;
        myTable[1] = nameProcess;
        myTable[2] = jTableProcessList.getValueAt(c, 6).equals(true) ? initialTimeProcess + 8 : initialTimeProcess;
        myTable[3] = Quantum;
        myTable[4] = timeProcess + " Segundos";
        myTable[5] = "Terminado";
        secondModel.addRow(myTable);
        jTableTerminatedProcess.setModel(secondModel);
        quantityProcess++;
        jTextQuantityProcess.setText(quantityProcess + " Terminados");
        jTextTotalTime.setText(timeProcess + " Segundos");
    }

    /***
     * Elimina los registros de la tabla de procesos
     * @param c indice de inicio de borrado
     */
    public void erase(int c) { //Elimina los registros de la tabla procesos
        jTableProcessList.setValueAt(0, c, 0);
        jTableProcessList.setValueAt("*****", c, 1);
        jTableProcessList.setValueAt("0", c, 2);
        jTableProcessList.setValueAt("0", c, 3);
        jTableProcessList.setValueAt("0", c, 4);
        jTableProcessList.setValueAt("******", c, 5);
        jTableProcessList.setValueAt(false, c, 6);
        jTableProcessList.setValueAt(0, c, 7);
    }


    /***
     * Calcula el porcentaje de la barra de progreso
     * @param initialTime Tiempo inicial
     * @param residue residuo
     */
    public void updateDataProgressBar(int initialTime, int residue) {
        int initialTimeProcess = initialTime;
        int value = 100 / initialTime;
        int percentage = 100 - (value * residue);
        ProgressBarValue = percentage;
        jTextProgressBarPercentage.setText(ProgressBarValue + "%");
    }


    /***
     * Vuelve a pintar el panel del proceso actual
     */
    public void paint() {
        jPActualProcess.setValue(ProgressBarValue);
        jPActualProcess.repaint();
    }

    /***
     * Inicia la secuencia de procesos
     */
    public void startProcess() {
        jLabelInsertQuantum.setVisible(false);
        jLabelInsertTime.setVisible(false);
        jLabelInsertName.setVisible(false);
        jTextGetProcessTime.setVisible(false);
        jTextGetQuantum.setVisible(false);
        jTextGetProcessName.setVisible(false);
        JBtnAccept.setVisible(false);
        jCheckBox2.setVisible(false);
        jBtnStart.setVisible(false);
        jLabelPercentage.setVisible(false);
    }

    /**
     * Proceso que bloquea un proceso iniciando un hilo independiente para realizar esta acción.
     *
     * @param i
     */
    private void blockProcess(int i) {
        jTableProcessList.setValueAt("Bloqueado", i, 5);
        paint();
        isWaiting = true;
        state.put(i, 1);
        new BlockerThread(i).start();
    }

    /***
     * Logica del simulador
     */
    private class myThread implements Runnable { //Objeto de tipo myThread con extension ejectubale
        @Override
        public void run() {
            int flag = 1; //Estado de while que indica si se puede seguir o no
            int i = 0; // contador de while
            while (true) {
                while (i < count) { //Recorrer las filas
                    chargeData(i);
                    if (leftTimeProcess != 0 && leftTimeProcess > Quantum && !isWaiting) { //Ejecutando Procesos
                        for (int c = 1; c <= Quantum; c++) {
                            if (timeWait == leftTimeProcess) {
                                if (state.get(i) == 2) {
                                    timeWait = -1;
                                    leftTimeProcess--;
                                    timeProcess++;
                                    continue;
                                } else if (state.get(i) == 0) {
                                    blockProcess(i);
                                    sleepMethod();
                                    break;
                                } else if (state.get(i) == 1) {
                                    break;
                                }
                            }
                            jTableProcessList.setValueAt("Procesando", i, 5);
                            leftTimeProcess--;
                            updateDataProgressBar(initialTimeProcess, leftTimeProcess);
                            paint();
                            jTableProcessList.setValueAt(String.valueOf(leftTimeProcess), i, 4);
                            timeProcess++;
                            sleepMethod();

                        }
                        if (!isWaiting) jTableProcessList.setValueAt("Listo", i, 5);
                        if (leftTimeProcess == 0) {
                            jTableProcessList.setValueAt("Terminado", i, 5);
                            paint();
                            showReport(i);
                            erase(i);
                            jPActualProcess.setValue(0);
                        }
                    } else {
                        if (leftTimeProcess > 0 && !isWaiting) {
                            while (leftTimeProcess > 0) {
                                if (timeWait == leftTimeProcess) {
                                    if (state.get(i) == 2) {
                                        timeWait = -1;
                                        leftTimeProcess--;
                                        timeProcess++;
                                        continue;
                                    } else if (state.get(i) == 0) {
                                        blockProcess(i);
                                        sleepMethod();
                                        break;
                                    } else if (state.get(i) == 1) {
                                        break;
                                    }
                                }
                                jTableProcessList.setValueAt("Procesando", i, 5);
                                leftTimeProcess--;
                                updateDataProgressBar(initialTimeProcess, leftTimeProcess);
                                paint();
                                jTableProcessList.setValueAt(String.valueOf(leftTimeProcess), i, 4);
                                timeProcess++;
                                sleepMethod();
                            }
                            if (!isWaiting) jTableProcessList.setValueAt("Listo", i, 5);
                        }
                        if (leftTimeProcess == 0 && Quantum != 0) {
                            jTableProcessList.setValueAt("Terminado", i, 5);
                            paint();
                            showReport(i);
                            erase(i);
                            jPActualProcess.setValue(0);
                        }
                    }
                    jTextNumberActualProcess.setText(""); //erase contenido
                    jTextProgressBarPercentage.setText("");
                    i++;
                }
                i = 0;
                jTextNumberActualProcess.setText(""); //erase contenido
                jTextProgressBarPercentage.setText("");
            }
        }
    }

    /**
     * Class that implements the thread that blocks the process
     */
    private class BlockerThread extends Thread {
        private final int i;

        public BlockerThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            if (isBlocked) {
                System.out.println("Bloqueado");
                int j = 0;
                while (j < 8) {
                    sleepMethod();
                    timeProcess++;
                    j++;
                }
                jTableProcessList.setValueAt("Listo", i, 5);
                paint();
                isWaiting = false;
                state.put(i, 2);
            }
        }
    }
}
