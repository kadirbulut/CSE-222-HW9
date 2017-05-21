import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class AbstractGraphExtended extends AbstractGraph {


    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
    }




    public int addRandomEdgesToGraph (int edgeLimit){

        Random rand=new Random();//random generator oluştur

        int number=(rand.nextInt(Integer.MAX_VALUE)%edgeLimit)+1; // kaç edge eklenecek ?

        // eklenecek edge sayısı 0 ise 0 olmayana kadar bir sayı üret
        while (number==0) {
            number = (rand.nextInt(Integer.MAX_VALUE) % edgeLimit) + 1;
        }

        int range=getNumV(); // eklenecek edgeler için bir aralık belirle (0-vertex sayısı)
        int result=0; //döndürülecek değer

        for(int i=0;i<number;i++){
            int s=rand.nextInt(Integer.MAX_VALUE)%(range); //random source değeri oluştur
            int d=rand.nextInt(Integer.MAX_VALUE)%(range); //random destination değeri

            //eğer var olan bir edge değil ise ekleme yap
            if(this.isEdge(s,d) == false && s!=d){
                this.insert(new Edge(s,d));
                //aslında resultu yalnız var olmayan edgelerde artırmak oluşturulan number sayısını 1 azaltmak var ise
                result++;
            }
        }

        return result; // eklenen sayıyı return et
    }


    public int [] breadthFirstSearch (int start){

        if(start > this.getNumV())
            return null;

            LinkedList myList = new LinkedList(); // temp değerler tutmak için
            LinkedList result = new LinkedList(); // sonuçları tutmak için

            //gezilen vertexleri kontrol etmek için
            boolean[] visitedEdges = new boolean[this.getNumV()];
            Arrays.fill(visitedEdges, false); // hepsini gezilmedi işaretle

            //start değerinden gezmeye başla
            visitedEdges[start] = true;
            myList.add(start);

            while (myList.size() != 0) {

                //resulta ekleme yap
                result.add(myList.get(0));
                myList.remove(myList.get(0));

                Iterator it = this.edgeIterator(start); //iteratör oluştur
                while (it.hasNext()) {
                    Edge edge = (Edge) it.next();
                    if (visitedEdges[edge.getDest()] == false) { // daha once gezilmedi ise
                        visitedEdges[edge.getDest()] = true;
                        myList.add(edge.getDest()); //temp e ekle gezdikçe
                        start = edge.getDest(); // start değerini değiştir gezdilen edge'in dest değeri ile
                        //bir nevi recursive oalrak ilerle
                    }
                }

            }
            //undirect graphlarda start değerinden öncesinide kontrol et yukarıdaki mantığın aynısı ile
            if(!this.isDirected()) {

                for (int i = 0; i < result.size(); i++) {
                    start = (int) result.get(i);
                    Arrays.fill(visitedEdges, false);

                    visitedEdges[start] = true;
                    myList.add(start);

                    while (myList.size() != 0) {

                        if (!result.contains(myList.get(0)))
                            result.add(myList.get(0));
                        myList.remove(myList.get(0));

                        Iterator it = this.edgeIterator(start);
                        while (it.hasNext()) {
                            Edge edge = (Edge) it.next();
                            if (visitedEdges[edge.getDest()] == false) {
                                visitedEdges[edge.getDest()] = true;
                                myList.add(edge.getDest());
                                start = edge.getDest();
                            }
                        }
                    }
                }
            }

            //integer arrayi oluştur ve bulunan tüm değerleri result linkedlistinden kopyala
            int[] array = new int[result.size()];
            for(int i=0;i<result.size();i++){
                array[i]=(int)result.get(i);
            }

            return array; // sonuç integer arrayini return et


    }

    public Graph [] getConnectedComponentUndirectedGraph (){

        if(this.isDirected() == false ){

                int type; // list yada matrix graph olduğunu tutmak için

                LinkedList all; // bulunan graphları tutmak için

                //eğer list graph ise graphları tutmak için arraylist oluştur
                if(this instanceof ListGraph) {
                     all = new LinkedList<ListGraph>();
                    type=1;

                }
                //eğer matrix graph ise graphları tutmak için arraylist oluştur
                else {
                    all = new LinkedList<MatrixGraph>();
                    type=2;
                }

                boolean[] flags = new boolean[this.getNumV()]; // tüm edgelein gezilip gezilmediğini kontrol etmek için boolean arrayi
                Arrays.fill(flags,false); //gezmeye başlamadan önce hepsini false ile doldur

                int vertex=0 ; // gezmeye 0 dan başla
                int control=0; // döngüyü kontrol etmek için

                while (control==0) {
                    int[] tempArr=breadthFirstSearch(vertex); //bread-first mantığıyla tüm edgeleri al
                    if(tempArr.length!=0){

                        if(type==1) { // eğer obje list graph ise elde edilen değerlerin büyüklüğünde bir listgraph objesi oluştur
                            ListGraph tempGraph = new ListGraph(tempArr.length, this.isDirected());

                            //arraydeki ilk değeri 0'a diğerlerinide ilk değer ile aralarındaki farka eşitleyerek yeni graph'a map etmek için değerleri bul
                            int first=tempArr[0];
                            int[] arr2 = tempArr.clone();
                            for(int i=0;i<tempArr.length;i++){
                                arr2[i]=tempArr[i]-first;

                            }

                            //map edilecek değerleri elde ettikten sonra ilk graphta böyle bir edge varmı kontrol et ve insert et
                            for(int i=0;i<arr2.length;i++){
                                for(int j=0;j<arr2.length;j++){
                                    if(!tempGraph.isEdge(arr2[i],arr2[j]) && this.isEdge(tempArr[i],tempArr[j])){
                                        tempGraph.insert(new Edge(arr2[i],arr2[j]));
                                    }
                                }
                            }
                            all.add(tempGraph); //elde edilen yeni graphı all objesine ekle
                        }
                        if(type==2) { // eğer obje matrix graph ise elde edilen değerlerin büyüklüğünde bir matrixgraph objesi oluştur
                           MatrixGraph tempGraph = new MatrixGraph(tempArr.length, this.isDirected());

                            //arraydeki ilk değeri 0'a diğerlerinide ilk değer ile aralarındaki farka eşitleyerek yeni graph'a map etmek için değerleri bul
                            int first=tempArr[0];
                            int[] arr2 = tempArr.clone();
                            for(int i=0;i<tempArr.length;i++){
                                arr2[i]=tempArr[i]-first;

                            }
                            //map edilecek değerleri elde ettikten sonra ilk graphta böyle bir edge varmı kontrol et ve insert et
                            for(int i=0;i<arr2.length;i++){
                                for(int j=0;j<arr2.length;j++){
                                    if(!tempGraph.isEdge(arr2[i],arr2[j]) && this.isEdge(tempArr[i],tempArr[j])){
                                        tempGraph.insert(new Edge(arr2[i],arr2[j]));
                                    }
                                }
                            }
                            all.add(tempGraph); //elde edilen yeni graphı all objesine ekle
                        }

                        //gezilen edge'lerin flaglarını true yap
                        for(int i=0;i<tempArr.length;i++){
                            flags[tempArr[i]]=true;
                        }
                    }


                    // tüm edgeler gezildimi diye kontrol et
                    int flagControl=1;

                    for(int i=0;i<flags.length;i++){
                        if(flags[i] == false) {
                            flagControl = -1;
                            vertex=i;
                            break;
                        }
                    }

                    if(flagControl == -1) {
                        control = 0; // gezilmediyse döngüye devam et
                    }
                    else // gezildiyse döngüden çık
                        control=1;

                }

                //eğer list graph ise list graph arrayini return et
                if (type == 1) {
                    ListGraph[] arr = new ListGraph[all.size()];
                    for(int i=0;i<arr.length;i++) {
                        arr[i] = (ListGraph) all.get(i);
                    }
                    return arr; //graph arrayini return et
                }
                //eğer matrix graph ise matrix graph arrayini return et
                if (type == 2) {
                    MatrixGraph[] arr = new MatrixGraph[all.size()];
                    for(int i=0;i<arr.length;i++) {
                        arr[i] = (MatrixGraph) all.get(i);
                    }
                    return arr; //graph arrayini return et
                }
            }

        return null; //null return et directed bir graph ise
    }



    public boolean isBipartiteUndirectedGraph () {


        if (this.isDirected()) // directed bir obje ise çalışma
            return false;

        if (this.getNumV() < 2) //en az iki gruba ayrılabilmesi lazım
            return false;

        boolean result = true; //return edilecek sonuç

        LinkedList<Edge> myedges = new LinkedList<>();
        LinkedList reds = new LinkedList(); // ilk grup
        LinkedList blues = new LinkedList(); // ikinci grup
        LinkedList temp = new LinkedList(); //kırmızılar için temp değerler
        LinkedList temp2 = new LinkedList(); //maviler için temp değerler


        for(int index=0;index<this.getNumV();index++) {

            Iterator it = this.edgeIterator(index);
            //başlangıç vertexini reds grubuna at
            if(index==0)
                reds.add(index);

            //başlangıç değerinnin komşularını bul
            while (it.hasNext()) {
                Edge edge = (Edge) it.next();
                if(!myedges.contains(edge))
                    myedges.add(edge);
                if(!myedges.contains(new Edge(edge.getDest(),index))) {
                    temp.add(edge.getDest());
                }
            }

            //başlangıç değerinin komşularını blues grubuna at
            for(int i=0;i<temp.size();i++){
                if(!blues.contains(temp.get(i))) //varsa yeniden eklemeye gerek yok
                    blues.add(temp.get(i));
            }

            //daha sonra blues grubuna eklenen değerlerin komşularını nul
            for(int i=0;i<temp.size();i++) {
                Iterator it1 = this.edgeIterator((int)temp.get(i));
                while (it1.hasNext()) {
                    Edge edge = (Edge) it1.next();
                    temp2.add(edge.getDest());
                }
            }

            //son olarak bulunan komşularıda tekrar reds grubuna ekle
            for(int i=0;i<temp2.size();i++){
                if(!reds.contains(temp2.get(i)))  //varsa yeniden eklemeye gerek yok
                    reds.add(temp2.get(i));
            }

            temp.clear();
            temp2.clear();
        }

        //reds ve blues gruplarının ortak eleman içerip içermediğini kontrol et, içeriyorsa return değerini false yap
        for (int i=0;i<reds.size();i++){
            if(blues.contains(reds.get(i))){
                result=false;
                break;
            }
        }


        return result; //sonucu return et


    }


    public void writeGraphToFile (String fileName) throws IOException {

        ArrayList<Integer> temp = new ArrayList();
        Edge edge = null;
        FileWriter fw = new FileWriter(fileName); //filewriterı iç
        int all = this.getNumV();
        fw.write(String.valueOf(all)+"\n"); //başa vertex sayısını yaz


        //iteratör yardımı ile tüm vertexleri sırası ile gez ve bağlantılarını yaz
        for (int i=0;i<this.getNumV();i++){
            Iterator it = this.edgeIterator(i);
            while (it.hasNext()){
                edge = (Edge) it.next();
                temp.add(edge.getDest()); // elde edilen dest değerlerini bir arraylistte tut
            }
            //dosyaya yazmak için dest değerlerini sırala çünkü sonradan eklenenler daha küçük olabilir
            Collections.sort(temp);
            for(int j=0;j<temp.size();j++)
                fw.write(edge.getSource()+" "+temp.get(j)+"\n"); // dest değerlerini sırası ile dosyaya yaz

            temp.clear(); // arraylisti temizle
        }

        fw.close(); //filewriterı kapat

    }


}
