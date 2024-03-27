import java.util.ArrayList;

public class Awards {
    // Ödüllerde sadece materyal ve yiyecek içecekler olacak para olmayacak
    // Para mekan ve düşmanlara ödül int_award parametresine verilecek
    // Buradaki nesneler satılabilecek
    private String name;
    private int id;
    private int selling_price;

    private int count;
    private boolean flag;
    public Awards(String name, int id, int selling_price,int count,boolean flag) {
        this.name = name;
        this.id = id;
        this.selling_price = selling_price;
        this.count = count;
        this.flag = true;
    }

    public static Awards[] awards(){
        Awards[] awardList = new Awards[15];// Hepsi satılır , bazıları craft edilir, bazıları yenilip can doldurur
        awardList[0] =new Awards("Diş",1,10,0,true);//
        awardList[1] =new Awards("Kürk",2,30,0,true); // craft yapılır
        awardList[2] =new Awards("Elma",3,1,0,true); // 10 Can arttırır.
        awardList[3] =new Awards("Morfin",4,20,0,true);//
        awardList[4] =new Awards("Mantar",5,5,0,true);//
        awardList[5] =new Awards("Ekmek",6,2,0,true);//
        awardList[6] =new Awards("Odun",7,2,0,true);//
        awardList[7] =new Awards("Tahıl",8,10,0,true);//
        awardList[8] =new Awards("Çalıntı Eşya",9,25,0,true);//
        awardList[9] =new Awards("Donmuş Yürek",10,50,0,true);
        awardList[10]=new Awards("Mistik Diş",10,40,0,true);
        awardList[11]=new Awards("Yakut",11,70,0,true);
        awardList[12]=new Awards("Zindan Anahtarı",12,5,0,true);
        return awardList;
    }

    public static Awards getAwardObjByName(String name){

        for(Awards award:Awards.awards()){
            if(award.getName().equals(name)){
                return award;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(this.count<0){
            this.count = 0;
        }
        else{
            this.count=count;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
