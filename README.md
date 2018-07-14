# İçerik

- [Nasıl İndirilir ve Kurulum Yapılır](#nasıl-İndirilir-ve-kurulum-yapılır)
- [Ana Sayfa Link Değiştirme](#ana-sayfa-link-değiştirme)
- [Kategori Menüsü Değiştirme](#kategori-menüsü-değiştirme)
  - [Kategori Arka Planı Değiştirme](#kategori-arka-planı-değiştirme)
- [Alt Menüyü Değiştirme](#alt-menüyü-değiştirme)
- [Uygulama Yazılarını Değiştirme](#uygulama-yazılarını-değiştirme)
- [Splash Screen Görüntüsünü Değiştirme/Kaldırma](#splash-screen-görüntüsünü-değiştirmekaldırma)
- [Uygulamaya Logo Eklemek](#uygulamaya-logo-eklemek)
- [Ayarlar Menüsünü Değiştirmek](#ayarlar-menüsünü-değiştirmek)
- [ADMOB Ekleme](#admob-ekleme)
- [Renkleri Değiştirme](#renkleri-değiştirme)
- [One Signal Ayarlarını Yapma](#one-signal-ayarlarını-yapma)

# Nasıl İndirilir ve Kurulum Yapılır

![6](https://user-images.githubusercontent.com/25686023/42723706-d4dbb828-876b-11e8-91d5-0a138411ea1b.png)

Resimdeki butona bastıp projeyi indirin. Daha sonrasında Android Studio açıp aşağıdaki resimde görülen butona basın.

![7](https://user-images.githubusercontent.com/25686023/42723716-308b9008-876c-11e8-8611-6fdcb4cd30f9.png)

Daha sonra indirdiğiniz dosyayı bulup onu seçmeniz yeterlidir.

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
<p></p>

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
## Kategori Arka Planı Değiştirme

**fragment_categories.xml** dosyasında resimde görülen alanı değiştirmeniz yeterlidir.

![12](https://user-images.githubusercontent.com/25686023/42723853-df63b166-876f-11e8-87b0-50d1f61ce72d.png)

# Alt Menüyü Değiştirme

**bottomnavigation_menu.xml** Dosyasından Alt Menüyü değiştirebiliriz.

- Örnek Menü Ekleme:
```xml
<item
    android:title=“BAŞLIK BURAYA“
    android:id="@+id/ID BURAYA“
    android:icon="@drawable/RESIM BURAYA“
    app:showAsAction="always"/>
```

<p><b>BURAYA ID</b> yazan kısıma başlığa uygun bir id yazın.</p>
<p><b>BURAYA BAŞLIK</b> yazan kısıma istediğiniz başlığı yazın.</p>
<p><b>BURAYA RESIM</b> yazan kısma drawable dosyasındaki iconun adını yazın.</p>

Daha sonrasında 

![3](https://user-images.githubusercontent.com/25686023/42723528-1377fffa-8768-11e8-9007-ec4243e2ace7.png)

**MainActivity.java** içerisindeki resimdeki alana menü itemlarınıza göre değiştirin. Örneğin:

```xml
<item
    android:title="@string/ana_sayfa"
    android:id="@+id/ana_sayfa”
    android:icon="@drawable/ic_home_black_24dp"
    app:showAsAction="always"/>
```
Itemı için aşağıdaki eklemeyi yapmanız gerekir.

```java
case R.id.ana_sayfa:
    fragmentClass=MainPage.class;
    break;
```

# Uygulama Yazılarını Değiştirme

**strings.xml** dosyası içinden istediğiniz şekilde değişiklik yapabilirsiniz.

# Splash Screen Görüntüsünü Değiştirme/Kaldırma

### Splash Screen Görüntüsünü Değiştirmek 

Splash screen görüntüsünü değiştirmek için **splash_screen.xml** dosyasını açıp aşağıdaki resimdeki alana resmi eklemeniz yeterlidir.

![10](https://user-images.githubusercontent.com/25686023/42723808-a236c644-876e-11e8-817a-ee683805c712.png)

### Splash Screen Görüntüsünü Kaldırmak

**MainActivity.java** dosyası içindeki resimde görülen alanı silmeniz yeterlidir.

![11](https://user-images.githubusercontent.com/25686023/42723828-2faa5568-876f-11e8-9307-418927b6eaee.png)

# Uygulamaya Logo Eklemek

[Bu linkteki](https://www.youtube.com/watch?v=5Y4plQv8c4s) videoyu izleyerek hızlı bir şekilde değişikliklerinizi yapabilirsiniz.

# Ayarlar Menüsünü Değiştirmek
[Bu](https://github.com/jrvansuita/MaterialAbout) linke tıklayarak detaylı bilgi alabilirsiniz. Kullanımı çok basittir.

# ADMOB Ekleme
<p><b>fragment_mainpage_fragment.xml</p>
<p>fragment_categories.xml</p></b>
Dosyalarında bulunan resimdeki yerleri Admob ID'niz ile değiştirin.

![4](https://user-images.githubusercontent.com/25686023/42723640-8a5b6e8e-876a-11e8-9e35-3457e4200ff0.png)

# Renkleri Değiştirme

Renkleri değiştirmek için **colors.xml** dosyasındaki renkleri değiştirmeniz yeterlidir.

![5](https://user-images.githubusercontent.com/25686023/42723666-d65689ea-876a-11e8-9f7c-e5ac68a903b6.png)

# One Signal Ayarlarını Yapma
