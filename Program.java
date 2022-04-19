//Tomasz Koczar-5


/*To do:

*/
import java.util.Scanner;

/**Klasa reprezentujaca tablice w programie*/ 
class Tablica{

    /***************Pola****************/

    /*Ilosc wierszy oraz kolum w tablicy*/
    private int wiersze,kolumny;

    /*Tablica*/
    private int[][] tablica;

    /*Wiersz pomocniczy*/
    private int[] wierszPomocniczy ;

    /*Dane maksymalnej podtablicy*/
    private int suma,
    /*Poczatek wiersza*/
    i,
    /*Koniec wiersza*/
    j,
    /*Poczatek kolumny*/
    k,
    /*Koniec kolumny*/
    l,
    /*Liczba elementow*/
    elem;
    

    /*************Metody **********/

    /*Konstruktor*/
    public Tablica(Scanner sc){
        /*Pobieramy ilosc wierszy i kolumn*/
        wiersze = sc.nextInt();
        //sc.next();
        kolumny = sc.nextInt();

        /*Inicjalizumemy tablice*/
        tablica = new int[wiersze][kolumny];

        /*I wierszPomocniczy  pomocniczy*/
        wierszPomocniczy = new int[kolumny];

        /*Oraz dane tablicy*/
        for(int i=0;i < wiersze; i++){
            for(int j=0;j< kolumny;j++)
                tablica[i][j] = sc.nextInt();
        }
        /*Oraz dane maxPt*/
        /*Dane maksymalnej podtablicy*/
    suma=-1;
    /*Poczatek wiersza
    i=-1;
    /*Koniec wiersza
    j=-1;
    /*Poczatek kolumny
    k=-1;
    /*Koniec kolumny
    l=-1;
    /*Liczba elementow*/
    elem=0;
    }

    public void znajdzMaxPTwWierszuPomocniczym(int wierszPoczatkowy, int wierszKoncowy){
        /*Poczatek, koniec i suma j oraz ilosc elementow
        maxymalnej podtablicy zawartej w a[0..i-1]*/
        int b1=0,e1=0,max1=-1,ilosc_elementow=0;

        /*Poczatek i suma maxymalniej
         podtablicy konczoncej w i-1*/
        int b2=0,curr=0;

        for(int i=0;i<kolumny;i++){
            /*Jesli suma sumy maksymalnego przedzialu
            zakonczonego w i-1 oraz i */
            curr+=wierszPomocniczy[i];
            //System.out.println("Sprawdzam: curr "+curr);
            if(curr<0|| /*Ignorujemy nie pierwszy
            zerowy wynik*/(curr==0 && max1>=0)){
                /*To maksymalny przedzial 
                zkonczony w i jest pusty*/
                curr=0;
                b2=i+1;
            }
            else{
                /*Sprawdzamy czy najlepsza podtablica
                zakonczona w i-1 powiekoszna o i 
                jest najepsza w wierszu*/
                if(curr>max1){
                    max1=curr;
                    /*Usuwamy potencjalne zera z przodu*/
                    if(wierszPomocniczy[b2]==0 && b2!=i){
                        b1=b2+1;
                    }
                    else
                        b1=b2;
                    e1=i;
                    ilosc_elementow = (e1-b1+1)*(wierszKoncowy-wierszPoczatkowy+1);
                    }
                else if(curr==max1){
                    /*Sprawdzamy licze elementow*/
                    if((i-b2+1)*(wierszKoncowy-wierszPoczatkowy+1)<ilosc_elementow){
                    max1=curr;
                    b1=b2;
                    e1=i;
                    ilosc_elementow = (i-b2+1)*(wierszKoncowy-wierszPoczatkowy+1);
                    }
                }
                
            }

            /*Sprawdzamy czy podtablica z wiersza 
                jest naljepsza badz rowna najelpszej w calej tablicy*/
            if(max1>suma || (max1==suma && ilosc_elementow<elem)){

                    suma=max1;
                    k=b1;
                    l=e1;
                    this.i=wierszPoczatkowy;
                    j=wierszKoncowy;
                    elem=ilosc_elementow;
                }
            /*else if(max1==suma){
                /*Jesli tak to ktoraj ma minej elemnetow
                if(ilosc_elementow<elem){
                    /*Jesli ta z wiersza ma mniej to jest najepsza
                    suma=max1;
                    k=b1;
                    l=e1;
                    this.i=wierszPoczatkowy;
                    j=wierszKoncowy;
                    elem=ilosc_elementow;

                }
            }*/
        }
    }

    public void ZnajdzMaxPTwTablicy(){
        /*Dodajemy wiersze do wiersza pomocniczego
         i oibliczamy maxPT w wierszu pomocniczym*/
        for(int w0=0;w0<wiersze;w0++){
            /*Zerujemy tablice pomocnicza*/
            for(int t=0;t<kolumny;t++)
                wierszPomocniczy[t]=0;

            for(int w=w0 ;w<wiersze; w++ ){
                for(int k=0;k<kolumny;k++){
                    wierszPomocniczy[k]+=tablica[w][k];
                }    
                znajdzMaxPTwWierszuPomocniczym(w0, w);           
            }
        }

    }

    /*Sprawdza czy maxPt jest pusta*/
    public boolean  czyPusta(){
        for(int w=0;w<wiersze;w++){
            for(int k=0;k<kolumny;k++){
                if(tablica[w][k]>-1)
                    return false;
            }
        }
        suma=0;
        return true;
    }

    
    

    public void prezentuj(){

        if(suma>-1){
            System.out.print("n = "+ wiersze + " m = "+kolumny
            +", s = "+ suma + ", mst = a[" + i + ".." + j +"][" + k + ".."
            + l + "]\n");
        }
        else{
            System.out.print("n = "+ wiersze + " m = "+kolumny
            +", s = 0, mst is empty\n");
        }
    }

    public void prezentujPusta(){
        System.out.print("n = "+ wiersze + " m = "+kolumny
        +", s = "+ suma + ", mst is empty\n");
    }
}

/**Klasa glowna programu*/
class Source{

    /*Wejscie do programu*/
    public static Scanner sc = new Scanner(System.in);

    /********** Program ***********/
    public static void main(String[] args){

        /*Pobieramy liczbe zestawow danych wejsciowych*/
        int liczbaDanychWejsciowych = sc.nextInt();

        /*Urchamiamy petle odpowiednia ilosc razy */
        for(int i=0;i<liczbaDanychWejsciowych;i++){
           
            /*Tworzymy tablice*/
            sc.nextInt();
            sc.next(":");
            Tablica tablica = new Tablica(sc);
            /*if(tablica.czyPusta()){
                System.out.print(i+1 + ": ");
                tablica.prezentujPusta();
            }*/
            
            //else{
                /*Znajdujemy maksymalna podtablice*/
                tablica.ZnajdzMaxPTwTablicy();

                /*Prezentujemy wyniki*/
                System.out.print(i+1 + ": ");
                tablica.prezentuj();
            //}

        }
    }
    /*Test jawny zwraca: 
    1: n = 1 m = 6, s = 11, mst = a[0..0][1..3]
    2: n = 2 m = 5, s = 4, mst = a[1..1][4..4]
    3: n = 2 m = 5, s = 4, mst = a[1..1][0..0]
    4: n = 2 m = 5, s = 4, mst = a[0..0][3..3]
    5: n = 2 m = 5, s = 0, mst is empty
    6: n = 2 m = 5, s = 0, mst = a[0..0][0..0]
    7: n = 1 m = 6, s = 0, mst = a[0..0][3..3]
    */

    /*Test : 
    1
    1 : 1 4
    -5 0 5 0
    Zwraca:
    1: n = 1 m = 4, s = 5, mst = a[0..0][2..2]
    */
}