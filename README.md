# Ana Sayfa Link Değiştirme

![1](https://user-images.githubusercontent.com/25686023/42708450-089716ac-86e6-11e8-92a0-e5fcd5818c01.png)

```web_url="<WEBSITE LINKI>``` kısmına websitesi adresinizi yazınız.

# Kategori Menüsü Değiştirme

**navigation_menu.xml** Dosyasından Kategori menüsündeki menüleri değiştirebilirsiniz. Örnek menü ekleme yöntemleri:

Menü arası ayıraç ile
```xml
<group
    android:id="@+id/first”> 
    <item
        android:title=“BURAYA BAŞLIK”
        android:id="@+id/ID BURAYA“
        android:icon="@drawable/RESIM”
        app:showAsAction="always"/>
</group>
```
Menü arası ayıraç olmadan
```xml
<item
        android:title=“BURAYA BAŞLIK”
        android:id="@+id/BURAYA ID“
        android:icon="@drawable/BURAYA RESIM”
        app:showAsAction="always"/>
```

<p><b>BURAYA ID</b> yazan kısıma başlığa uygun bir id yazın.</p>
<p><b>BURAYA BAŞLIK</b> yazan kısıma istediğiniz başlığı yazın.</p>
<p><b>BURAYA RESIM</b> yazan kısma drawable dosyasındaki iconun adını yazın.</p>

**categories.java**
![2](https://user-images.githubusercontent.com/25686023/42708601-7ed5389e-86e6-11e8-938a-bd0201d86296.png)

**navigation.menu.xml** dosyasından eklemeleri yaptıktan sonra **categories.java** dosyasındaki resimdeki alanı menü itemlarınıza göre değiştirin. Örneğin:


```xml

<item
        android:title=“BAŞLIK”
        android:id="@+id/ID_BURAYA“
        android:icon="@drawable/RESIM”
        app:showAsAction="always"/>
```
Menü item için aşağıdaki eklemeyi yapabilirsinizi

```java
case R.id.ID_BURAYA:
    webviewSetup(“<ISTEDIGINIZ LINK>“);
    break;
```


