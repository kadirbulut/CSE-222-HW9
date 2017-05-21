import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try {

            //test methodlarını çağır

            test1DirectedListGraph();

            test2UndirectedListGraph();

            test3DirectedMatrixGraph();

            test4UndirectedMatrixGraph();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test1DirectedListGraph() throws IOException {

        System.out.println("------------------------------TEST 1 FOR DIRECTED LIST GRAPH ------------------------------");
        //graph 1'i dosyadan oku ve directed bir list graph objesi oluştur
        File graphData = new File("graph_1.txt");
        Scanner scnr = new Scanner(graphData);
        ListGraph lgObj = (ListGraph) AbstractGraph.createGraph(scnr,true, "List");
        scnr.close();

        //list graph objesine random olarak ekleme yap ve sonucu test1-1.txt adlı dosyaya yazdır
        //sonucu yazdırırken writeGraphToFile methodunu kullan
        System.out.println("1.graph'a "+lgObj.addRandomEdgesToGraph(10)+" adet random edge eklendi.");
        lgObj.writeGraphToFile("test1-1.txt");


        //graph 2'yi dosyadan oku ve directed bir list graph objesi daha oluştur
        File graphData2 = new File("graph_2.txt");
        Scanner scnr2 = new Scanner(graphData2);
        ListGraph lgObj2 = (ListGraph) AbstractGraph.createGraph(scnr2,true, "List");
        scnr2.close();

        //list graph objesine random olarak ekleme yap ve sonucu test1-2.txt adlı dosyaya yazdır
        //sonucu yazdırırken writeGraphToFile methodunu kullan
        System.out.println("2.graph'a "+lgObj2.addRandomEdgesToGraph(10)+" adet random edge eklendi.");
        lgObj2.writeGraphToFile("test1-2.txt");



        //lgObj list graph objesi için bread first search methodu ile her vertex için sonuçları ekrana yazdır
        System.out.println("1. graph için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");
        //lgObj2 list graph objesi için bread first search methodu ile her vertex için sonuçları ekrana yazdır

        System.out.println("2. graph için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr2 = lgObj2.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr2.length;j++){
                System.out.print(arr2[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("------------------------------------------------------------------------------------------");

    }

    public static void test2UndirectedListGraph() throws IOException {
        System.out.println("----------------------------TEST 2 FOR UNDIRECTED LIST GRAPH -----------------------------");

        // 3 FARKLI DOSYAYI OKU VE 3 FARKLI UNDIRECTED LIST GRAPH OBJESİ OLUŞTUR

        //graph 1'i dosyadan oku ve undirected bir list graph objesi oluştur
        File graphData = new File("graph_1.txt");
        Scanner scnr = new Scanner(graphData);
        ListGraph lgObj = (ListGraph) AbstractGraph.createGraph(scnr,false, "List");
        scnr.close();

        //graph 2'yi dosyadan oku ve undirected bir list graph objesi oluştur
        File graphData2 = new File("graph_2.txt");
        Scanner scnr2 = new Scanner(graphData2);
        ListGraph lgObj2 = (ListGraph) AbstractGraph.createGraph(scnr2,false, "List");
        scnr.close();

        //graph 3'ü dosyadan oku ve undirected bir list graph objesi oluştur
        File graphData3 = new File("graph_3.txt");
        Scanner scnr3 = new Scanner(graphData3);
        ListGraph lgObj3 = (ListGraph) AbstractGraph.createGraph(scnr3,false, "List");
        scnr.close();


        //3 OBJE İÇİNDE BAĞLI BİLEŞENLERİNİN OLUP OLMADIĞINI TEST ET VE VARSA AYRI AYRI DOSYALARA YAZDIR

        Graph[] temp =  lgObj.getConnectedComponentUndirectedGraph();

        System.out.println("1. objenin "+temp.length+" adet bağlı bileşeni var.");

        Graph[] temp2 =  lgObj2.getConnectedComponentUndirectedGraph();

        System.out.println("2. objenin "+temp2.length+" adet bağlı bileşeni var.");

        Graph[] temp3 =  lgObj3.getConnectedComponentUndirectedGraph();

        System.out.println("3. objenin "+temp3.length+" adet bağlı bileşeni var.");


        for (int i=0;i<temp.length;i++){
            ListGraph obj = (ListGraph)temp[i];
            obj.writeGraphToFile("Test2FirstObjComponentResult"+(i+1)+".txt");
        }

        for (int i=0;i<temp2.length;i++){
            ListGraph obj = (ListGraph)temp2[i];
            obj.writeGraphToFile("Test2SecondObjComponentResult"+(i+1)+".txt");
        }


        for (int i=0;i<temp3.length;i++){
            ListGraph obj = (ListGraph)temp3[i];
            obj.writeGraphToFile("Test2ThirdObjComponentResult"+(i+1)+".txt");
        }

        //3 OBJENINDE BIPARTITE OLUP OLMADIĞINI KONTROL ET VE EKRANA YAZ

        if(lgObj.isBipartiteUndirectedGraph())
            System.out.println("1. obje bipartite graph.");
        else
            System.out.println("1. obje bipartite graph değil.");


        if(lgObj2.isBipartiteUndirectedGraph())
            System.out.println("2. obje bipartite graph.");
        else
            System.out.println("2. obje bipartite graph değil.");


        if(lgObj3.isBipartiteUndirectedGraph())
            System.out.println("3. obje bipartite graph.");
        else
            System.out.println("3. obje bipartite graph değil.");



        //3 OBJEYİDE BREAD_FIRST_SEARCH OLARAK GEZ VE TÜM VERTEXLERİ EKRANA YAZ

        System.out.println("1. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");

        System.out.println("2. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");

        System.out.println("3. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }


        //3 OBJEYEDE RANDOM EDGELER EKLE VE SONUÇLARINI DOSYALARA YAZ

        lgObj.addRandomEdgesToGraph(6);
        lgObj2.addRandomEdgesToGraph(6);
        lgObj3.addRandomEdgesToGraph(6);

        lgObj.writeGraphToFile("Test2-FirstObj-AfterRandom.txt");
        lgObj2.writeGraphToFile("Test2-SecondObj-AfterRandom.txt");
        lgObj3.writeGraphToFile("Test2-ThirdObj-AfterRandom.txt");


        System.out.println("------------------------------------------------------------------------------------------");

    }


    public static void test3DirectedMatrixGraph() throws IOException {
        System.out.println("------------------------------TEST 3 FOR DIRECTED LIST GRAPH ------------------------------");

        //graph 1'i dosyadan oku ve directed bir matrix graph objesi oluştur
        File graphData = new File("graph_1.txt");
        Scanner scnr = new Scanner(graphData);
        MatrixGraph lgObj = (MatrixGraph) AbstractGraph.createGraph(scnr,true, "Matrix");
        scnr.close();

        //1.matrix graph objesine random olarak ekleme yap ve sonucu test3-1.txt adlı dosyaya yazdır
        //sonucu yazdırırken writeGraphToFile methodunu kullan
        System.out.println("1.graph'a "+lgObj.addRandomEdgesToGraph(10)+" adet random edge eklendi.");
        lgObj.writeGraphToFile("test3-1.txt");


        //2.matrix graph'ı dosyadan oku ve directed bir matrix graph objesi daha oluştur
        File graphData2 = new File("graph_2.txt");
        Scanner scnr2 = new Scanner(graphData2);
        MatrixGraph lgObj2 = (MatrixGraph) AbstractGraph.createGraph(scnr2,true, "Matrix");
        scnr2.close();


        //2.matrix graph objesine random olarak ekleme yap ve sonucu test3-2.txt adlı dosyaya yazdır
        //sonucu yazdırırken writeGraphToFile methodunu kullan
        System.out.println("2.graph'a "+lgObj2.addRandomEdgesToGraph(10)+" adet random edge eklendi.");
        lgObj2.writeGraphToFile("test3-2.txt");


        //lgObj matrix graph objesi için bread first search methodu ile her vertex için sonuçları ekrana yazdır
        System.out.println("1. graph için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");

        //lgObj2 matrix graph objesi için bread first search methodu ile her vertex için sonuçları ekrana yazdır
        System.out.println("2. graph için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr2 = lgObj2.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr2.length;j++){
                System.out.print(arr2[j]+" ");
            }
            System.out.println("\n");
        }



        System.out.println("------------------------------------------------------------------------------------------");
    }

    public static void test4UndirectedMatrixGraph() throws IOException {
        System.out.println("-------------------------TEST 4 FOR UNDIRECTED MATRIX GRAPH ------------------------------");

        // 3 FARKLI DOSYAYI OKU VE 3 FARKLI UNDIRECTED MATRIX GRAPH OBJESİ OLUŞTUR

        //graph 1'i dosyadan oku ve undirected bir matrix graph objesi oluştur
        File graphData = new File("graph_1.txt");
        Scanner scnr = new Scanner(graphData);
        MatrixGraph lgObj = (MatrixGraph) AbstractGraph.createGraph(scnr,false, "Matrix");
        scnr.close();

        //graph 2'yi dosyadan oku ve undirected bir matrix graph objesi oluştur
        File graphData2 = new File("graph_2.txt");
        Scanner scnr2 = new Scanner(graphData2);
        MatrixGraph lgObj2 = (MatrixGraph) AbstractGraph.createGraph(scnr2,false, "Matrix");
        scnr.close();

        //graph 3'ü dosyadan oku ve undirected bir matrix graph objesi oluştur
        File graphData3 = new File("graph_3.txt");
        Scanner scnr3 = new Scanner(graphData3);
        MatrixGraph lgObj3 = (MatrixGraph) AbstractGraph.createGraph(scnr3,false, "Matrix");
        scnr.close();


        //3 OBJE İÇİNDE BAĞLI BİLEŞENLERİNİN OLUP OLMADIĞINI TEST ET VE VARSA AYRI AYRI DOSYALARA YAZDIR

        Graph[] temp = lgObj.getConnectedComponentUndirectedGraph();

        System.out.println("1. objenin "+temp.length+" adet bağlı bileşeni var.");

        Graph[] temp2 = lgObj2.getConnectedComponentUndirectedGraph();

        System.out.println("2. objenin "+temp2.length+" adet bağlı bileşeni var.");

        Graph[] temp3 = lgObj3.getConnectedComponentUndirectedGraph();

        System.out.println("3. objenin "+temp3.length+" adet bağlı bileşeni var.");


        for (int i=0;i<temp.length;i++){
            MatrixGraph obj = (MatrixGraph) temp[i];
            obj.writeGraphToFile("Test4FirstObjComponentResult"+(i+1)+".txt");
        }

        for (int i=0;i<temp2.length;i++){
            MatrixGraph obj = (MatrixGraph) temp2[i];
            obj.writeGraphToFile("Test4SecondObjComponentResult"+(i+1)+".txt");
        }


        for (int i=0;i<temp3.length;i++){
            MatrixGraph obj = (MatrixGraph) temp3[i];
            obj.writeGraphToFile("Test4ThirdObjComponentResult"+(i+1)+".txt");
        }


        //3 OBJENINDE BIPARTITE OLUP OLMADIĞINI KONTROL ET VE EKRANA YAZ

        if(lgObj.isBipartiteUndirectedGraph())
            System.out.println("1. obje bipartite graph.");
        else
            System.out.println("1. obje bipartite graph değil.");


        if(lgObj2.isBipartiteUndirectedGraph())
            System.out.println("2. obje bipartite graph.");
        else
            System.out.println("2. obje bipartite graph değil.");


        if(lgObj3.isBipartiteUndirectedGraph())
            System.out.println("3. obje bipartite graph.");
        else
            System.out.println("3. obje bipartite graph değil.");


        //3 OBJEYİDE BREAD_FIRST_SEARCH OLARAK GEZ VE TÜM VERTEXLERİ EKRANA YAZ

        System.out.println("1. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");

        System.out.println("2. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }

        System.out.println("***************************");

        System.out.println("3. graph objesi için bread-first-search:\n");

        for(int i=0;i<lgObj.getNumV();i++) {
            int[] arr1 = lgObj.breadthFirstSearch(i);
            System.out.println(i+". vertex için bread first search:");
            for(int j=0;j<arr1.length;j++){
                System.out.print(arr1[j]+" ");
            }
            System.out.println("\n");
        }


        //3 OBJEYEDE RANDOM EDGELER EKLE VE SONUÇLARINI DOSYALARA YAZ

        lgObj.addRandomEdgesToGraph(6);
        lgObj2.addRandomEdgesToGraph(6);
        lgObj3.addRandomEdgesToGraph(6);

        lgObj.writeGraphToFile("Test4-FirstObj-AfterRandom.txt");
        lgObj2.writeGraphToFile("Test4-SecondObj-AfterRandom.txt");
        lgObj3.writeGraphToFile("Test4-ThirdObj-AfterRandom.txt");



        System.out.println("------------------------------------------------------------------------------------------");

    }

}
