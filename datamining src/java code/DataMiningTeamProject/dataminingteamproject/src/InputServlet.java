import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


@WebServlet ("/input")


public class InputServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    public InputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
      response.setContentType("text/html"); 
      request.setCharacterEncoding("utf8"); 
      response.setCharacterEncoding("utf8"); 
      String unih = request.getParameter("univ"); //string
      String gpah = request.getParameter("gpa");
      String toeich = request.getParameter("toeic");  
      String tossh = request.getParameter("toss"); 
      String opich = request.getParameter("opic"); //string
      String forlangh = request.getParameter("forlang"); 
      String certh = request.getParameter("cert"); 
      String awardh = request.getParameter("award"); 
      String internh = request.getParameter("intern"); 
      String forexph = request.getParameter("forexp"); 
      String volh = request.getParameter("vol"); 
      
      double univ = 0;
      double gpa = 0;
      double toeic = 0;
      double toss = 0;
      double opic = 0;
      double forlang = 0;
      double cert = 0;
      double award = 0;
      double intern = 0;
      double forexp = 0;
      double vol = 0;

      
      gpa = Double.parseDouble(gpah);
      toeic = Double.parseDouble(toeich);
      toss = Double.parseDouble(tossh);
      forlang = Double.parseDouble(forlangh);
      cert = Double.parseDouble(certh);
      award = Double.parseDouble(awardh);
      intern = Double.parseDouble(internh);
      forexp = Double.parseDouble(forexph);
      vol = Double.parseDouble(volh); 

      PrintWriter out = response.getWriter(); 
      
      
      //새로운 학교 인스턴스를 학교 속성의 인덱스로 변환
      if (unih.equals("metro")) {
         univ = 0.0;
      }
      else if (unih.equals("for")) {
         univ = 1.0;
      }
      else if (unih.equals("country")) {
         univ = 2.0;
      }
      else if (unih.equals("seoul")) {
         univ = 3.0;
      }
      
      if (opich.equals("N")) {
         opic = 0.0;
      }
      
      
      //새로운 오픽 인스턴스를 오픽 속성의 인덱스로 변환
      else if (opich.contentEquals("IL")) {
         opic = 1.0;
      }
      
      else if (opich.contentEquals("IMONE")) {
         opic = 2.0;
      }
      
      else if (opich.contentEquals("IMTWO")) {
         opic = 3.0;
      }
      
      else if (opich.contentEquals("IMTHREE")) {
         opic = 4.0;
      }
      
      else if (opich.contentEquals("IH")) {
         opic = 5.0;
      }
      
      else if (opich.contentEquals("AL")) {
         opic = 6.0;
      }
      
      
      //새로운 토스 인스턴스를 토스 속성의 인덱스로 변환
      if (tossh.equals("0")) {
    	  toss = 0.0;
      }
      
      else if (tossh.equals("1")) {
    	  toss = 1.0;
      }
      
      else if (tossh.equals("2")) {
    	  toss = 2.0;
      }
      
      else if (tossh.equals("3")) {
    	  toss = 3.0;
      }
      
      else if (tossh.equals("4")) {
    	  toss = 4.0;
      }
      
      else if (tossh.equals("5")) {
    	  toss = 5.0;
      }
      
      else if (tossh.equals("6")) {
    	  toss = 6.0;
      }
      
      else if (tossh.equals("7")) {
    	  toss = 7.0;
      }
      
      else if (tossh.equals("8")) {
    	  toss = 8.0;
      }

       DataSource source = null;
      try {
         source = new DataSource("./spec.arff");
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
         Instances trainDataset = null;
      try {
         trainDataset = source.getDataSet();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
         trainDataset.setClassIndex(trainDataset.numAttributes()-1);
         
         
         //univ
         List<String> f1 = new ArrayList<String>(4);
         f1.add("METRO");
         f1.add("FOR");
         f1.add("COUNTRY");
         f1.add("SEOUL");
         
         //tos
         List<String> f2 = new ArrayList<String>(9);
         f2.add("0");
         f2.add("1");
         f2.add("2");
         f2.add("3");
         f2.add("4");
         f2.add("5");
         f2.add("6");
         f2.add("7");
         f2.add("8");
         
         //opic
         List<String> f3 = new ArrayList<String>(7);
         f3.add("N");
         f3.add("IL");
         f3.add("IMONE");
         f3.add("IMTWO");
         f3.add("IMTHREE");
         f3.add("IH");
         f3.add("AL");
         
         //class
         List<String> f4 = new ArrayList<String>(4);
         f4.add("KAKAO");
         f4.add("SAMSUNG");
         f4.add("KT");
         f4.add("LGUPLUS");
         
         Attribute Univ = new Attribute("univ", f1);
         Attribute Gpa = new Attribute("gpa");
         Attribute Toeic = new Attribute("toeic");
         Attribute Toss = new Attribute("toss",f2);
         Attribute Opic = new Attribute("opic",f3);
         Attribute Forlang = new Attribute("forlang");
         Attribute Cert = new Attribute("cert");
         Attribute Award = new Attribute("award");
         Attribute Intern = new Attribute("intern");
         Attribute Forexp = new Attribute("forexp");
	     Attribute Vol = new Attribute("vol");
         Attribute Comp = new Attribute("comp", f4);  
         
         ArrayList<Attribute> allAttributes = new ArrayList <Attribute>(5);
         allAttributes.add(Univ); allAttributes.add(Gpa); allAttributes.add(Toeic); allAttributes.add(Toss);
         allAttributes.add(Opic); allAttributes.add(Forlang); allAttributes.add(Cert); allAttributes.add(Award);
         allAttributes.add(Intern); allAttributes.add(Forexp);  allAttributes.add(Vol); allAttributes.add(Comp);
         
         Evaluation eval = null;
      try {
         eval = new Evaluation(trainDataset);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
         

         
         Instances newDataset  = new Instances("newDataset", allAttributes, 0);
         double[] newdata = new double [] {univ, gpa, toeic, toss, opic, forlang, cert, award, intern, forexp, vol};
         Instance newInstance = new DenseInstance(1.0, newdata);
         newDataset.add(newInstance);
         newDataset.setClassIndex(newDataset.numAttributes() - 1);
         
         J48 decT = new J48();
         try {
         decT.buildClassifier(trainDataset);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

         double predDT = 0;
      try {
         predDT = decT.classifyInstance(newDataset.instance(newDataset.size() - 1));
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
         if(predDT == 0.0) out.println("recommend KAKAO to you");
         else if(predDT == 1.0) out.println("recommend SAMSUNG to you");
         else if(predDT == 2.0) out.println("recommend KT to you");
         else if(predDT == 3.0) out.println("recommend LGUPLUS to you");
           
         try {
         eval.crossValidateModel(decT, trainDataset, 10, new Random(1));
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
      }


   
}