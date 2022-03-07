package paketlerim;
import java.util.ArrayList;
/**
 * @author 191307093judyNdotoniNkwama
 */

public enum KelimeListesi {
/* 
    Not : - Anlamlari(Kelime tanımları en az bilgi içeren'den en çok bilgi içeren'e şekilde sıralanacak) 
*/
    
//kelimeler
    
    //4 harfli kelimeler  
    acil("acİl", "Arapça", anlamTab("Hemen yapılması gereken","Çabuk davranma zorunluluğu","ivedi anlamına gelen kelime","Hastanelerde vakit yitirilmeden bakılması gereken hastaların ilk tedavilerinin yapıldığı yer")),
    laik("laİk", "Farsça", anlamTab("Ruhanî olmayan kimse", "Dini olmayan şey", "Devlet işlerini dinden ayrı tutan","Din işlerini devlet işlerine karıştırmayan")),
    zevk("zevk", "Arapça", anlamTab("çok sevinip keyiflenme","Hoşa giden veya çekici bir şeyin elde edilmesinden, düşünülmesinden doğan hoş duygu")),
    veri("verİ", "Türkçe", anlamTab("Bilişimde, olgu, kavram veya komutların, iletişim, yorum ve işlem için elverişli biçimli gösterimi", "Bir problemde bilinen, belirtilmiş anlatımlardan bilinmeyeni bulmaya yarayan şey", "Bir sonuca varabilmek için gerekli bilgi")),
    
    //5 harfli kelimeler  
    günah("GÜNAH","Farsça",anlamTab("Cezayı gerektiren amel", "Dine aykırı iş", "Dince suç sayılan iş veya davranış")),
    kabin("KABİN", "Fransizca", anlamTab("Küçük ve özel bölme", "Plâjda soyunma yeri", "Gemilerde, uçaklarda, uzay gemilerinde küçük bölme")),
    olası("olası", "Türkçe", anlamTab("Mühtemelel anlamına gelen", "Görünüşe göre olacağı sanılan", "Bir olayın olma şansın oranı")),
    //ihmal("İhmal", "Arapça", anlamTab("Gereken ilgiyi göstermeme", "önem vermemek", "konsidere etmeme")),
    
    //6 harfli kelimeler  
    ezcane("ezcane", "Türkçe", anlamTab("İlaçların yapıldığı ve satıldığı yer")),
    dalgıç("dalgıç", "Türkçe", anlamTab("Balık adam","Genellikle özel donanımla su yüzeyi altında çalışmayı meslek edinen kimse, kurbağa adam", "Birinden habersiz bir şey almak huyunda olan kimse")),
    hafiza("hafİza", "Türkçe", anlamTab("Bilgisayarın kullandığı belleklerdir","Ezberleme, koruma kuvveti", "Muhafaza eden")),
    rasizm("rasİzm", "İngilizce", anlamTab("İnsanların toplumsal özelliklerini biyolojik, ırksal özelliklerine indirgeyerek bir ırkın başka ırklara üstün olduğunu öne süren öğreti", "Irkçılık anlamına gelen")),
    
    //7 harfli kelimeler       
    tüketim("tüketİm", "Türkçe", anlamTab("Üretilen veya yapılan şeylerin kullanılıp harcanması", "Ticaret mallarının kulanılması")),
    uğursuz("uğursuz","Türkçe", anlamTab("kadersiz, meymenetsiz olan", "Arkasından kötü şeyler götüren olay")),
    palmiye("palmİye","Fransizca", anlamTab("Anayurdu Amerika'da ve diğer bölgelerde bir çok çeşidi olan, sert gövdeli, büyük yapraklı bir ağaç", "Palmiyegillerden olan ağaçların genel adı")),
    cehalet("cehalet", "Türkçe", anlamTab("Bilmezlik, nâdanlık, ilimden ve her nevi müsbet mâlûmatdan habersiz olma")),
    
    //8 harfli 
    buldozer("buldozer", "Fransızca", anlamTab("Önündeki geniş bıçakla toprağı sıyırıp kaldıran, tekerlekli veya paletli bir yol makinesi")),
    rakiplik("rakiplik", "Türkçe", anlamTab("Birbirine rakip olma durumu, rekabet")),
        
    randıman("randıman", "Fransızca", anlamTab("Verim. Çalıştırılan, işletilen, bakılan bir şeyin verdiği sonuç veya bu sonucun niceliği")),     
    
    mahkûmane("mahkûmane", "Arapça", anlamTab("Mahkûma yaraşır bir biçimde")),
    aberasyon("aberasyon", "Fransızca", anlamTab("bir sapmanın tanımı tuhaf bir eylem ya da zihinsel bir durum gibi anormal ya da beklenmeyen bir şey")),
    
    radyofizik("radyofizik", "Fransızca", anlamTab("Radyoelektriğe ilişkin olayları inceleyen bilim dalı")), 
    çakozlamak("çakozlamak", "Türkçe", anlamTab("Uygunsuz bir durumu fark etmek")),     
    ; 
    
    //kelimelerin Özellikleri
    private final String kelime;
    private final int uzunluk;
    private final String koken;
    private final String[] anlamlarTablosu;
    
    //Getters ve Setters
    public String getKelime() {
        return kelime;
    }

    public String[] getAnlamlarTablosu() {
        return anlamlarTablosu;
    }
      
    //İç değişkenler
    private final ArrayList gonderilmisHarfIndiksleri = new ArrayList();
    private int gonderilmisAnlamSayisi = -1;
    
    //yapıcı metodu 
    private KelimeListesi(String pKelime, String pKoken, String[] pAnlamlarTablosu){
        kelime = pKelime;
        uzunluk = kelime.length();
        koken = pKoken;
        anlamlarTablosu = pAnlamlarTablosu;    
    }
     
    private static String[] anlamTab(String... anlamlar){
        return anlamlar;
    }
    
    //Nesne metotları
    public String anlamGonder(int i){
        if( i < this.anlamlarTablosu.length){
            return this.anlamlarTablosu[i];
        }else{
            return this.anlamlarTablosu[this.anlamlarTablosu.length -1 ];
        }
    }
    
    public int harfIndilsiIste(){
        return (int)Math.floor(Math.random() * this.kelime.length());
    }

    @Override public String toString(){
        String anlamlar = "\n";
        for(String a : this.anlamlarTablosu){
            anlamlar += "\t - " + a + "\n";
        }  
        String str = "Kelime : " + this.kelime + "\nUzunluk : " + this.uzunluk + "\nKök : " + this.koken + 
        "\nAnlamlar : " + anlamlar;
        return str;
    }
}
