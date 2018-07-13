# WebviewProject

# Ana Sayfa Link Değiştirme

<img src="http://prntscr.com/k618q4">

web_url kısmına websitesi adresi

Kategori Menüsü Değiştirme
navigation_menu.xml Dosyasından Kategori menüsündeki menüleri değiştirebilirsiniz. Değişiklik yapılacak yer http://prntscr.com/k61efr

Menü arası ayıraç ile
```xml
<group
    android:id="@+id/first”> 
    <item
        android:title=“BAŞLIK”
        android:id="@+id/ID BURAYA“
        android:icon="@drawable/RESIM”
        app:showAsAction="always"/>
</group>
```

Menü arası ayıraç olmadan
```xml
<item
        android:title=“BAŞLIK”
        android:id="@+id/BURAYA ID“
        android:icon="@drawable/BURAYA RESIM”
        app:showAsAction="always"/>
```


“BURAYA ID” yazan kısıma başlığa uygun bir id yazın.
Başlık kısmını istediğiniz gibi değiştirin.
“BURAYA RESIM” yazan kısma drawable dosyasındaki iconun adını yazın.

Categories—http://prntscr.com/k619ku

navigation.menu.xml dosyasından eklemeleri yaptıktan sonra categories.java dosyasındaki resimdeki alanı menü itemlarınıza göre değiştirin. Örneğin:
<item
        android:title=“BAŞLIK”
        android:id="@+id/ID_BURAYA“
        android:icon="@drawable/RESIM”
        app:showAsAction="always"/>

case R.id.ID_BURAYA:
    webviewSetup(“<ISTEDIGINIZ LINK>“);
    break;
