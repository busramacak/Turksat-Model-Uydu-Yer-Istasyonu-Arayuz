package yerIstasyonu;


import com.fazecast.jSerialComm.SerialPort;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.skins.DashboardSkin;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class FXMLDocumentController implements Initializable {

    
    @FXML
    private ImageView logo;

    @FXML
    private ImageView bulunmaktapng;

    @FXML
    private ImageView yukselistepng;

    @FXML
    private ImageView inmektepng;

    @FXML
    private ImageView beklemedepng;

    @FXML
    private ProgressBar pilGerilimi2;

    @FXML
    private ProgressBar pilGerilimi1;

    //@FXML
   // private Button kameraButon;

   // @FXML
    //private Button ayrilButon;

    @FXML
    private Button videoGonderButon;

    @FXML
    private Button baglanButon;

    @FXML
    private Button haritaButon;

    @FXML
    private Button simulasyonButon;

    @FXML
    private Button kalibrasyonButon;

    @FXML
    private Text takimNo;

    @FXML
    private Text paketSayisi;

    @FXML
    private Text gondermeZamani;

    @FXML
    private Text yukseklik;

    @FXML
    private Text sicaklik;

    @FXML
    private Text basinc;

    @FXML
    private Text GPSLatitude;

    @FXML
    private Text GPSLongitude;

    @FXML
    private Text GPSAltitude;

    @FXML
    private Text uyduStatusu;

    @FXML
    private Text hiz;

    @FXML
    private Text pitch;

    @FXML
    private Text roll;

    @FXML
    private Text pil1;

    @FXML
    private Text pil2;

    @FXML
    private Text yaw;

    @FXML
    private Text donusSayisi;

    @FXML
    private Text videoAktarimBilgisi;

    @FXML
    private LineChart<?, ?> basincGrafik;

    @FXML
    private LineChart<?, ?> sicaklikGrafik;

    @FXML
    private LineChart<?, ?> yukseklikGrafik;

    @FXML
    private LineChart<?, ?> inisHiziGrafikk;

    @FXML
    private StackPane root;

    @FXML
    private SubScene scene;

    @FXML
    private StackPane root2;

    @FXML
    private SubScene scene2;

    @FXML
    private CheckBox gostergeCheck;

    @FXML
    private CheckBox termometreCheck;

    @FXML
    private StackPane rootharita;

    @FXML
    private BorderPane rootHarita;

    @FXML
    private SubScene sceneharita;
    
    @FXML
    private AnchorPane roootharita;

    @FXML
    private SubScene sceneeharita;
    
   @FXML
    private StackPane gyroroot;

    @FXML
    private SubScene gyroscene;
    
   
    Button update = new Button("Haritaya Bağlan");

    
    
    
    Grafik grafikVeri = new Grafik();
    ArrayList<String>liste=new ArrayList<>();
    int paketSayisiInt=0;
    int WINDOW_SIZE=20;
    String paketSayisiString;

    
    
    
    XYChart.Series seriHiz=new XYChart.Series();
    XYChart.Series seriYukselik=new XYChart.Series();
    XYChart.Series seriBasinc=new XYChart.Series();
    XYChart.Series seriSicaklik=new XYChart.Series();

      
    Float sicaklikFloat,inisHiziFloat,yukseklikFloat,basincFloat;
    Double gyroXDouble,gpsX=1.0,gpsY=1.2;
    
    
    double latGps;
    double longGps;
    MyBrowser myBrowser;
    double lat;
    double lon;
    int pililk=100;

    
    
  
    
    
    int satir =1;
    SerialPort port;
    SerialPort ports[] =SerialPort.getCommPorts();
    int portSec=0;
    
    
    
    
    
    
    
    String konum = "C:\\Users\\büş\\Desktop\\Telemetri Kayıt\\telemetriExcel.csv";
    
    
    
    
    
    
    Cylinder silindir = new Cylinder(25,90,30); 
    final TextField latitude = new TextField("" + gpsX * 1.00007);
    final TextField longitude = new TextField("" +gpsY* 1.00007);
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    ArrayList<String> videoarray=new ArrayList<>();
//    String[] videodizi = new String[300000];
    
    Double pil1Double;
    Double pil2Double;
    
    Gauge gauge = new Gauge();     
    Gauge gauge2=new Gauge();
    ExcelKayıt excel=new ExcelKayıt();
    sekil döndürme=new sekil();
     
    
    
    
     
        @FXML
    void haritaClick(ActionEvent event) {
        
         myBrowser = new MyBrowser();
        
        Stage haritaStage = new Stage();
        
        Scene haritaScene = new Scene(myBrowser,410,270); 
        haritaStage.setTitle("GPS");
        haritaStage.getIcons().add(new Image(this.getClass().getResourceAsStream("15.png")));
        haritaStage.setScene(haritaScene);
        haritaStage.show();
    }

    private void scene(StackPane root, int i, int i0) { throw new UnsupportedOperationException("Not supported yet."); }
    private void scene2(StackPane root, int i, int i0) { throw new UnsupportedOperationException("Not supported yet."); }

    private void gyroscene(StackPane gyroroot, int WIDTH, int HEIGHT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

   
    class MyBrowser extends Pane {
        public MyBrowser() {
    
            final URL urlGoogleMaps = getClass().getResource("html.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setJavaScriptEnabled(true);
            webEngine.setOnAlert((WebEvent<String> e) -> {
                System.out.println(e.toString());
            });
            
            getChildren().add(webView);
            
             update.setOnAction((ActionEvent ee) -> {
               
               lat = gpsX;
                lon = gpsY;
            
                System.out.printf("%.2f %.2f%n", lat, lon);
                
                webEngine.executeScript("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                );
            });
            

           HBox toolbar  = new HBox();
            toolbar.getChildren().addAll(update);
             getChildren().addAll(toolbar);
        }
    }
   
    
    
    
    
    
    
      
    @FXML
    void kalibrasyonClick (ActionEvent event) {
        Kalib kalib = new Kalib();
        kalib.start();
    }
    
    class Kalib extends Thread{
        public void run(){
            port=ports[portSec];

            PrintWriter output = new PrintWriter(port.getOutputStream());

            int dur =0;
            while(dur ==0){

                output.print("k"); 
                output.flush();

                dur = 1;
            }
        }
    }
    
    
    
    
    
    
    
    @FXML
    void gostergeTikladi(ActionEvent event) {
        gosterge a=new gosterge();
        a.start();
       
        root.getChildren().addAll(gauge);
        root.setStyle("-fx-background-color:#bebebe");
        scene(root, 300, 250);

    }
    
    
    
    
    
    
    
    @FXML
    void termometreTikladi(ActionEvent event) {
        
        termometre c=new termometre();
        c.start();
       
        root2.getChildren().addAll(gauge2);
        root2.setStyle("-fx-background-color:#bebebe");
        scene2(root2, 300, 250);
    }
    
    
    
    
    
    

    
    class gosterge extends Thread{
        public void run(){
       
            gauge.setSkin(new DashboardSkin(gauge));  //ModernSkin : you guys can change the skin
            gauge.setTitle("Model Uydu Hız");  //title
            gauge.setUnit("m/sn");  //unit
            gauge.setUnitColor(Color.WHITE);
            gauge.setDecimals(0);  
            

            //gauge.setAnimationDuration(500);

            gauge.setValueColor(Color.WHITE);  
            gauge.setTitleColor(Color.WHITE);  
            gauge.setSubTitleColor(Color.WHITE);  
            gauge.setBarColor(Color.rgb(0, 150, 151));  
            gauge.setNeedleColor(Color.RED);  
            gauge.setThresholdColor(Color.RED);  //color will become red if it crosses thereshold value
            gauge.setThreshold(1);
            gauge.setThresholdVisible(true);
            gauge.setTickLabelColor(Color.rgb(151, 151, 151));  
            gauge.setTickMarkColor(Color.WHITE);  
            gauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL); 
        }
    }
    
    class termometre extends Thread{
        public void run(){
       
            gauge2.setSkin(new DashboardSkin(gauge2));  //ModernSkin : you guys can change the skin
            gauge2.setTitle("Model Uydu Sıcaklık");  //title
            gauge2.setUnit("°C");  //unit
            gauge2.setUnitColor(Color.WHITE);
            gauge2.setDecimals(0);  
            
            //gauge.setAnimationDuration(500);

            gauge2.setValueColor(Color.WHITE);  
            gauge2.setTitleColor(Color.WHITE);  
            gauge2.setSubTitleColor(Color.WHITE);  
            gauge2.setBarColor(Color.rgb(0, 150, 151));  
            gauge2.setNeedleColor(Color.RED);  
            gauge2.setThresholdColor(Color.RED);  //color will become red if it crosses thereshold value
            gauge2.setThreshold(1);
            gauge2.setThresholdVisible(true);
            gauge2.setTickLabelColor(Color.rgb(151, 151, 151));  
            gauge2.setTickMarkColor(Color.WHITE);  
            gauge2.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL); 
        }
    }
    
    
    
    
    
    
    @FXML
    void ayrilClick(ActionEvent event) {
        Ayril ayril = new Ayril();
        ayril.start(); 
    }
    
    class Ayril extends Thread{
        public void run(){
            port=ports[portSec];
       
            PrintWriter output = new PrintWriter(port.getOutputStream());

            int dur =0;
            while(dur ==0){

                output.print("a");
                output.flush();
                try {   
                    Thread.sleep(1000);
                    output.printf("b");
                    output.flush();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                output.print("b");
                output.flush();

                dur = 1;
            }
        }
    }

    
    
    
       @FXML   
    void kameraClick(ActionEvent event) {
        Kamera kameraObje=new Kamera();
        kameraObje.start();
    }

    class Kamera extends Thread{
        public void run(){
            try {
                Process p = Runtime.getRuntime().exec(new String[] {"C:\\Users\\büş\\Desktop\\ax\\VideoView.exe"});
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
  
    
    
    
    
    @FXML
    void simulasyonClick(ActionEvent event){
        
        int WIDTH = 266;
        int HEIGHT = 280;
 
        PhongMaterial kaplama=new PhongMaterial();
        
        kaplama.setDiffuseMap(new Image(getClass().getResourceAsStream("model.png")));
        silindir.setMaterial(kaplama);
       
        gyroroot.getChildren().add(silindir);
        
        Camera camera = new PerspectiveCamera(true);
        gyroscene(gyroroot,120,150);
        gyroscene.setFill(Color.SILVER);
        gyroscene.setCamera(camera);
 
        camera.translateZProperty().set(-400);
        camera.setNearClip(1);
        camera.setFarClip(1000);
        
      
     
    }
    
   class sekil{
        
        void ekle(){
             
            Rotate rotate = new Rotate(); 
            rotate.setAngle(20);
          
         
            silindir.getTransforms().addAll(rotate); 
            
        }
    }
    
    
    @FXML
    void videoClick(ActionEvent event) {
        döndürme.ekle();
    }
  
       
 
    
    
    
    @FXML
    void baglanClick(ActionEvent event) {
        Baglan baglanObje=new Baglan();
        baglanObje.start();
    }
    
    public class Baglan extends Thread{
        public void run(){

            System.out.println("Lütfen bir port seçiniz");

            int i=0;
            for(SerialPort port : ports){
                System.out.println(i++ +" "+port.getSystemPortName());
            }

            Scanner scan=new Scanner(System.in);
            port=ports[portSec];
            port.setBaudRate(9600);

            if(port.openPort()){
                System.out.println("Bağlantı başarılı");
            }
            else{
                System.out.println("bağlantı yok");
                return;
            }

            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000,0);
            Scanner data=new Scanner(port.getInputStream());
            
           
                 while(data.hasNextLine()){

                String bolunmus=data.nextLine().toString();
                String atarih[] = bolunmus.split(",");

                VerileriDagit veriObje=new VerileriDagit();

                for(String s:atarih){

                    liste.add(s);
                }
                if(liste.size()==21){

                    veriObje.start();
                    System.out.println(liste + " "  + liste.size());
                }
            }
                 
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 Baglan baglan2 = new Baglan();
                 
                 baglan2.start();
                 
        } 
    }
    
    class VerileriDagit extends Thread{
        public void run(){
            
            paketSayisiString=String.valueOf(paketSayisiInt);
          
    
            takimNo.setText(liste.get(0));
            paketSayisi.setText(liste.get(1));
            gondermeZamani.setText(liste.get(2)+" "+liste.get(3)+"/"+liste.get(4)+"/"+liste.get(5));
            basinc.setText(liste.get(6));
            yukseklik.setText(liste.get(7));
            hiz.setText(liste.get(8));
            sicaklik.setText(liste.get(9));
            pil1.setText(liste.get(10));
            pil2.setText(liste.get(11));
            GPSLatitude.setText(liste.get(12));
            GPSLongitude.setText(liste.get(13));
            GPSAltitude.setText(liste.get(14));
           
            pitch.setText(liste.get(16));
            roll.setText(liste.get(17));
            yaw.setText(liste.get(18));
            donusSayisi.setText(liste.get(19));
            videoAktarimBilgisi.setText(liste.get(20));
            
            
            basincFloat = Float.parseFloat(liste.get(6));
            yukseklikFloat = Float.parseFloat(liste.get(7));
            inisHiziFloat = Float.parseFloat(liste.get(8));
            sicaklikFloat = Float.parseFloat(liste.get(9));
            
            gpsX = Double.parseDouble(liste.get(12));
            gpsY = Double.parseDouble(liste.get(13));
            gyroXDouble = Double.parseDouble(liste.get(16));
            
            
            pil1Double=Double.parseDouble(liste.get(10));
            pil2Double=Double.parseDouble(liste.get(11));;
//            pilGerilimi1.setProgress(pil1Double/5.00);
//            pilGerilimi2.setProgress(pil2Double/5.00);


            if(inisHiziFloat<0){
                inisHiziFloat=inisHiziFloat*-1;
            }
            
            gauge.setValue(inisHiziFloat); //deafult position of needle on gauage
            gauge.setAnimated(true);
              
            gauge2.setValue(sicaklikFloat);
            gauge2.setAnimated(true);
            
            
            
            if(liste.get(15).equalsIgnoreCase("a")){
                uyduStatusu.setText("BEKLEMEDE");
                beklemedepng.setOpacity(1);
                yukselistepng.setOpacity(0.25);
                inmektepng.setOpacity(0.25);
                bulunmaktapng.setOpacity(0.25);
            }
            else if(liste.get(15).equalsIgnoreCase("b")){
                uyduStatusu.setText("YÜKSELMEDE");
                beklemedepng.setOpacity(0.25);
                yukselistepng.setOpacity(1);
                inmektepng.setOpacity(0.25);
                bulunmaktapng.setOpacity(0.25);
            }
            else if(liste.get(15).equalsIgnoreCase("c")){
                uyduStatusu.setText("model Uydu İniş");
                beklemedepng.setOpacity(0.25);
                yukselistepng.setOpacity(0.25);
                inmektepng.setOpacity(1);
                bulunmaktapng.setOpacity(0.25);
            }
            else if(liste.get(15).equalsIgnoreCase("d")){
                uyduStatusu.setText("Görev Yükü İniş");
                beklemedepng.setOpacity(0.25);
                yukselistepng.setOpacity(0.25);
                inmektepng.setOpacity(1);
                bulunmaktapng.setOpacity(0.25);           
            }
            else if(liste.get(15).equalsIgnoreCase("e")){
                uyduStatusu.setText("KURTARMADA");
                beklemedepng.setOpacity(0.25);
                yukselistepng.setOpacity(0.25);
                inmektepng.setOpacity(0.25);
                bulunmaktapng.setOpacity(1);
            }
            
            
            grafikVeri.GrafikVeriEkle(paketSayisiString, sicaklikFloat, basincFloat, yukseklikFloat, inisHiziFloat);
           
            if(seriHiz.getData().size()>=WINDOW_SIZE){
                seriSicaklik.getData().remove(0);
                seriYukselik.getData().remove(0);
                seriHiz.getData().remove(0);
                seriBasinc.getData().remove(0);
            }
            
            paketSayisiInt=Integer.parseInt(paketSayisiString);
            paketSayisiInt++;  
            excel.ekle();
            döndürme.ekle();
            
            if(liste.size()>=21){
               liste.clear();
            } 
        }
    } 
    
    
    class ExcelKayıt{
        public void ekle(){
            File file=new File(konum);
            if(!file.exists())
            {
                try {
                    WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(konum));
                    writableWorkbook.createSheet(konum,0);

                    writableWorkbook.write();
                    writableWorkbook.close();

                    Workbook aWorkBook = Workbook.getWorkbook(new File(konum));
                    WritableWorkbook aCopy = Workbook.createWorkbook(new File(konum), aWorkBook);

                    WritableSheet aCopySheet = aCopy.getSheet(0);



                    Label anotherWritableCell = new Label(0, 0, "Takım No");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(1, 0, "Paket Sayısı");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(2, 0, "Gönderme Zamanı");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(3,0, "Basınç");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(4,0, "Yükseklik");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(5,0, " İniş Hızı");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(6, 0, "Sıcaklık");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(7, 0, "1.Pil Gerilimi");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(8, 0, "2.Pil Gerilimi");
                    aCopySheet.addCell(anotherWritableCell);
                   
                    anotherWritableCell = new Label(9, 0, "Latitude");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell= new Label(10, 0, "Longitude");
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(11, 0, "Altitude");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(12, 0, "Uydu Statüsü");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(13, 0, "Pitch");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(14, 0, "Roll");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(15, 0, "Yaw");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(16, 0, "Dönüş Sayısı");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(17, 0, "Video Aktarım Bilgisi");
                    aCopySheet.addCell(anotherWritableCell);
                    
                    
                    
                    anotherWritableCell = new Label(0, satir, liste.get(0));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(1, satir, liste.get(1));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(2, satir, "  "+liste.get(2)+"\n"+liste.get(3)+"/"+liste.get(4)+"/"+liste.get(5));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(3, satir, liste.get(6));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(4, satir, liste.get(7));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(5, satir, liste.get(8));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(6, satir, liste.get(9));
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(7, satir, liste.get(10)); //pil gerilimi 1
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell= new Label(8, satir, liste.get(11)); // pil gerilimi 2
                    aCopySheet.addCell(anotherWritableCell);

                    anotherWritableCell = new Label(9, satir, liste.get(12)); //latitude
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(10, satir, liste.get(13)); //longitude
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(11, satir, liste.get(14)); //altitude
                    aCopySheet.addCell(anotherWritableCell);
                    
                    if(liste.get(15).equalsIgnoreCase("a")){
                        anotherWritableCell = new Label(12, satir, "BEKLEMEDE"); //statü
                        aCopySheet.addCell(anotherWritableCell);
                    }
                    else if(liste.get(15).equalsIgnoreCase("b")){
                        anotherWritableCell = new Label(12, satir, "YÜKSELMEKTE"); //statü
                        aCopySheet.addCell(anotherWritableCell);
                    }
                    else if(liste.get(15).equalsIgnoreCase("c")){
                        anotherWritableCell = new Label(12, satir, "Model Uydu İniş"); //statü
                        aCopySheet.addCell(anotherWritableCell);
                    }
                    else if(liste.get(15).equalsIgnoreCase("d")){
                        anotherWritableCell = new Label(12, satir, "Görev Yükü İniş"); //statü
                        aCopySheet.addCell(anotherWritableCell);         
                    }
                    else if(liste.get(15).equalsIgnoreCase("e")){
                        anotherWritableCell = new Label(12, satir, "KURTARMA"); //statü
                        aCopySheet.addCell(anotherWritableCell);
                    }

                    anotherWritableCell = new Label(13, satir, liste.get(16)); //pitch
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(14, satir, liste.get(17)); //roll
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(15, satir, liste.get(18)); //yaw
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(16, satir, liste.get(19)); //Dönüş Sayısı
                    aCopySheet.addCell(anotherWritableCell);
                    
                    anotherWritableCell = new Label(17, satir, liste.get(20)); //video aktarım bilgisi
                    aCopySheet.addCell(anotherWritableCell);
                    
                  
                    
                    satir++;

                    aCopy.write();
                    aCopy.close();
            
                } catch (IOException | WriteException | BiffException e) {}
            }
            
            try {

                Workbook aWorkBook = Workbook.getWorkbook(new File(konum));
                WritableWorkbook aCopy = Workbook.createWorkbook(new File(konum), aWorkBook);

                WritableSheet aCopySheet = aCopy.getSheet(0);



                Label anotherWritableCell = new Label(0, 0, "Takım No");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(1, 0, "Paket Sayısı");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(2, 0, "Gönderme Zamanı");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(3,0, "Basınç");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(4,0, "Yükseklik");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(5,0, " İniş Hızı");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(6, 0, "Sıcaklık");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(7, 0, "1.Pil Gerilimi");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(8, 0, "2.Pil Gerilimi");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(9, 0, "Latitude");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell= new Label(10, 0, "Longitude");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(11, 0, "Altitude");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(12, 0, "Uydu Statüsü");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(13, 0, "Pitch");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(14, 0, "Roll");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(15, 0, "Yaw");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(16, 0, "Dönüş Sayısı");
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(17, 0, "Video Aktarım Bilgisi");
                aCopySheet.addCell(anotherWritableCell);
                
                
                

                anotherWritableCell = new Label(0, satir, liste.get(0));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(1, satir, liste.get(1));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(2, satir, liste.get(2)+" "+liste.get(3)+"/"+liste.get(4)+"/"+liste.get(5));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(3, satir, liste.get(6));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(4, satir, liste.get(7));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(5, satir, liste.get(8));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(6, satir, liste.get(9));
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(7, satir, liste.get(10)); //pil gerilimi 1
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell= new Label(8, satir, liste.get(11)); // pil gerilimi 2
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(9, satir, liste.get(12)); //latitude
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(10, satir, liste.get(13)); //longitude
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(11, satir, liste.get(14)); //altitude
                aCopySheet.addCell(anotherWritableCell);

                if(liste.get(15).equalsIgnoreCase("a")){
                    anotherWritableCell = new Label(12, satir, "BEKLEMEDE"); //statü
                    aCopySheet.addCell(anotherWritableCell);
                }
                else if(liste.get(15).equalsIgnoreCase("b")){
                    anotherWritableCell = new Label(12, satir, "YÜKSELMEKTE"); //statü
                    aCopySheet.addCell(anotherWritableCell);
                }
                else if(liste.get(15).equalsIgnoreCase("c")){
                    anotherWritableCell = new Label(12, satir, "Model Uydu İniş"); //statü
                    aCopySheet.addCell(anotherWritableCell);
                }
                else if(liste.get(15).equalsIgnoreCase("d")){
                    anotherWritableCell = new Label(12, satir, "Görev Yükü İniş"); //statü
                    aCopySheet.addCell(anotherWritableCell);         
                }
                else if(liste.get(15).equalsIgnoreCase("e")){
                    anotherWritableCell = new Label(12, satir, "KURTARMA"); //statü
                    aCopySheet.addCell(anotherWritableCell);
                }

                anotherWritableCell = new Label(13, satir, liste.get(16)); //pitch
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(14, satir, liste.get(17)); //roll
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(15, satir, liste.get(18)); //yaw
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(16, satir, liste.get(19)); //Dönüş Sayısı
                aCopySheet.addCell(anotherWritableCell);

                anotherWritableCell = new Label(17, satir, liste.get(20)); //video aktarım bilgisi
                aCopySheet.addCell(anotherWritableCell);

                satir++;

                aCopy.write();
                aCopy.close();

            } catch (WriteException | IOException | BiffException e) {}
    }
    }
    
    
    class Grafik extends Thread{
        public void GrafikVeriEkle(String sure,Float sicaklik,Float basinc,Float yukseklik,Float inishizi){
           
                    
            seriSicaklik.getData().add(new XYChart.Data(sure,sicaklik));
            seriBasinc.getData().add(new XYChart.Data(sure,basinc));
            seriYukselik.getData().add(new XYChart.Data(sure,yukseklik));
            seriHiz.getData().add(new XYChart.Data(sure,inishizi));     
        }
       
        
        public void run(){
            
            sicaklikGrafik.getData().addAll(seriSicaklik);
            basincGrafik.getData().addAll(seriBasinc);
            inisHiziGrafikk.getData().addAll(seriHiz);
            yukseklikGrafik.getData().addAll(seriYukselik);

            sicaklikGrafik.setAnimated(false);
            basincGrafik.setAnimated(false);
            inisHiziGrafikk.setAnimated(false);
            yukseklikGrafik.setAnimated(false);
            
            sicaklikGrafik.setCreateSymbols(false);
            basincGrafik.setCreateSymbols(false);
            inisHiziGrafikk.setCreateSymbols(false);
            yukseklikGrafik.setCreateSymbols(false);
            
        
        }
    }
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Grafik verileriCiz =new Grafik();
        verileriCiz.start(); 
//        pilGerilimi1.setProgress(100.0);
//        pilGerilimi2.setProgress(100.0);
//
//        pilGerilimi1.setStyle(" -fx-accent: red;");
//        pilGerilimi2.setStyle(" -fx-accent: red;");
        
        gosterge a=new gosterge();
        a.start();
        
        termometre b=new termometre();
        b.start();
    } 
}