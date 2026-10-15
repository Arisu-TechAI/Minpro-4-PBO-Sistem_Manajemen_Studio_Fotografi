# Mini Project 4 PBO - Sistem Manajemen Studio Fotografi

Oleh :  

Nama    : Arizky Saputra   
NIM     : 2509116088  
Prodi   : Sistem Informasi  
Kelas   : C 2025

Program ini dibuat untuk memenuhi tugas Mini Project 4 mata kuliah Pemrograman Berbasis Objek (PBO) program studi S1 Sistem Informasi. 
Program ini mengimplementasikan konsep *Object-Oriented Programming* (OOP) menggunakan bahasa Java dan dikembangkan menggunakan aplikasi Apache NetBeans.  

# 1. Deskripsi Singkat Program  
<b>Sistem Manajemen Studio Fotografi</b> adalah aplikasi berbasis Console/Command Line Interface (CLI) berorientasi objek (Object-Oriented Programming) yang dikembangkan menggunakan bahasa pemrograman Java. 
Program ini dirancang untuk memodelkan dan mengelola proses bisnis transaksi dan reservasi jasa fotografi pada sebuah studio foto secara terstruktur.    

## Latar Belakang Tujuan Pembuatan Program  
**1. Masalah Operasional Industri Studio Fotografi**  

Usaha jasa studio fotografi modern terus berkembang seiring meningkatnya kebutuhan masyarakat akan dokumentasi momen penting (wisuda, *portrait*, hingga *wedding*). Namun, sebagian besar manajemen studio skala kecil hingga menengah masih mengandalkan pencatatan pesanan secara manual. Hal ini menimbulkan beberapa masalah operasional:
- **Risiko Duplikasi & Kesalahan Data:** Pencatatan manual rentan terhadap kesalahan penulisan ID pesanan, nomor telepon klien, atau jadwal sesi yang bentrok antar-pelanggan.
- **Kurangnya Transparansi Detail Paket:** Klien sering kali kesulitan membedakan spesifikasi antar-paket (misalnya beda durasi sesi untuk paket studio reguler vs jumlah kru untuk paket *event outdoor*).
- **Kesulitan Memantau Status Pembayaran:** Pihak studio sering kesulitan memisahkan transaksi mana yang masih berstatus *DP/Belum Lunas* dan mana yang sudah *Lunas*, yang berdampak pada ketidakpastian arus kas.

**Oleh karena itu**, program ini dibangun sebagai **solusi digitalisasi sistem reservasi** yang memungut data pemesanan secara otomatis, tervalidasi, dan terstruktur.  

**2. Tantangan Pengembangan Perangkat Lunak**  
Dari sisi akademis dan rekayasa perangkat lunak, pembuatan program ini menjadi sarana untuk menyelesaikan beberapa tantangan pengembangan aplikasi CLI:

- **Penerapan Arsitektur Bersih (MVC):** Mencegah penumpukan kode pada satu class utama (*spaghetti code*) dengan memisahkan *Model* (penyimpanan data), *View* (antarmuka input/output), dan *Controller* (logika bisnis).
- **Integritas Data melalui Validasi Input:** Menghindari *program crash* atau penyimpanan data yang asal-asalan saat pengguna salah memasukkan format teks, nomor telepon, atau opsi menu.
- **Kebutuhan Keamanan Data (Encapsulation):** Memastikan seluruh data sensitif milik klien dan nilai transaksi terproteksi dari akses luar yang tidak sah (*direct variable access*).
- **Skalabilitas Fitur (Inheritance & Polymorphism):** Memudahkan penambahan jenis layanan foto baru di masa depan tanpa harus merombak struktur kode yang sudah ada.

# 2. Penjelasan Struktur Package  
Pada pengerjaan Mini Project ini, dipisahkan menjadi 4 **Package** utama yang disesuaikan berdasarkan tugas dan fungsinya. Adapun Package dan Class yang dibuat adalah sebagai berikut :  

<img width="564" height="320" alt="Screenshot 2026-09-25 073226" src="https://github.com/user-attachments/assets/7ce09f37-cb07-45f8-a003-69ceb659b90a" />

- **Package _Main_**  
  Package ini berisi sebuah class yaitu **main.java** yang berisi method *main(string[] args)*. Tugas class ini hanya menginstansiasi sebuah class lain yaitu class **Menu** dan berfungsi untuk menjalankan antarmuka aplikasi, sehingga file utama dalam program dibuat bersih dari sistem logika bisnis.
  
- **Package _model_**  
  Package ini digunakan untuk menyimpan seluruh struktur data dari entitas, model objek dunia nyata, dan aturan bisnis yang ada. Fungsinya adalah mendefinisikan atribut-atribut data yang ada di dalam class nya dan menyimpan data tersebut dengan menerapkan konsep *Encapsulation*. Adapun class-class yang terdapat pada package ini yaitu :
  - **LayananFotografi.java (Super-class)** : Class ini digunakan sebagai *Parent* dari seluruh class yang memiliki atribut terkait tipe layanan jasa fotografi.  
  -  **PaketReguler.java (Sub-class)** : Class ini berfungsi untuk menyimpan atribut spesifik terkait jenis paket jasa fotografi di *Indoor/Studio*.
  -  **Paket Event.java (Sub-class)** : Class ini digunakan untuk menyimpan atribut spesifik terkait jenis paket jasa fotografi di *Outdoor*.
  -  **Client.java** : Class yang digunakan untuk menyimpan informasi data pelanggan, seperti ID, nama, dan nomor telepon.
  -  **Transaksi.java** : Class yang berfungsi sebagai lembar kuitansi/nota pesanan utuh. Class ini menggabungkan data pelanggan dari class Client dengan paket foto dari class LayananFotografi, lalu menambahkan informasi tanggal sesi foto serta status pembayaran (Belum Lunas/Lunas).
 
- **Package _controller_**
  Package ini bertindak sebagai pengontrol dari sistem yang ada dalam program Studio Fotografi ini. Fungsinya adalah mengelola keperluan operasional program yang ada pada class dibawahnya. Package ini memiliki sebuah class yaitu :
  - **Transaksi_Controller.java** : Fungsi class ini adalah mengelola lemari penyimpanan berkas digital (ArrayList). Tugas-tugas tersebut meliputi :  
      - Membuatkan nomor ID otomatis  
      - Menyiapkan data sampel awal  
      - Mencari data pesanan di dalam lemari berkas, menambahkan pesanan baru, serta mengubah status pembayaran pelanggan dari belum lunas menjadi lunas  

- **Package __View__**  
  Package ini adalah sebagai pengatur tampilan antarmuka dari program ini. Package ini memiliki class yaitu :
  - **Menu.java**
    Class ini bertindak sebagai tampilan antarmuka dari program. Tugasnya adalah menyapa pengguna di layar terminal, menampilkan pilihan menu, dan menerima ketikan input.  

# 3. Penjelasan Alur Program  
## A. **Start up Program**  
Saat program baru dibuka melalui class **main**, class ini bertindak sebagai titik masuk eksekusi (entry point). Tugas kelas dibatasi hanya untuk menginstansiasi class **Menu** yang berfungsi untuk menampilkan tampilan pilihan menu kepada pengguna, dan menerima input pilihan pengguna saat menjalankan program aplikasi.  
<img width="413" height="187" alt="Menu Awal startup" src="https://github.com/user-attachments/assets/6333e2ff-6278-4c50-a7cb-3c5516adadf4" />  

Untuk mengakses pilihan menu di atas, pengguna dapat mengetik angka pilihan menu pada kolom input (Misal angka "1" untuk menambah/membuat pesanan), maka program akan mengarahkan pengguna ke antarmuka yang sesuai dengan kebutuhan.  

## B. **Validasi Input & Antarmuka**  
Saat prorgam sedang dijalankan, sistem harus melakukan validasi terhadap input yang dilakukan oleh pengguna, apakah sudah sesuai dengan rancangan yang dibuat di dalam sistem atau tidak. Tujuannya adalah agar program dapat dijalankan sebagaimana mestinya sesuai kebutuhan pengguna, jika input yang dilakukan tidak valid, maka program akan mengembalikan pengguna ke tampilan sebelumnya dan meminta agar pengguna memasukan input yang sesuai dengan pilihan menu yang ada.    
<img width="380" height="209" alt="Contoh salah input" src="https://github.com/user-attachments/assets/07eca57e-6892-4efe-bc97-38d40f7210c1" />  

Kode program yang bertugas melakukan validasii input dapat diperhatikan pada contoh berikut :  
<img width="971" height="177" alt="Menu java" src="https://github.com/user-attachments/assets/61d932e1-ea48-47c4-9e63-cc1fe84917c2" />  

Kode di atas adalah program yang berfungsi melakukan validasi input Nomor Telepon pengguna saat membuat pesanan. Penerapan ekspresi reguler *(\\d{10,14})* digunakan untuk memastikan masukan pengguna hanya terdiri dari karakter angka dengan panjang 10 hingga 14 digit. Perulangan **while** menjaga aliran program tetap berjalan hingga syarat validasi terpenuhi.  

## C. **Penerapan Konsep _Auto-Generate ID_**  
<img width="623" height="198" alt="kode auto generate" src="https://github.com/user-attachments/assets/a36cbc34-545e-4a87-ba9a-aefb2e269283" />  

Pada class **Transaksi_Controller**, diterapkan konsep otomatisasi pada ID yang dibuat pada program, yaitu ID untuk Transaksi yang dilakukan, dan ID untuk Invoice hasil pembayaran. Penerapannya pada konde program, digunakan fungsi **String.Format()** yang berfungsi membuat pola penamaan ID secara terstruktur. Variabel **_counterTransaksi_** melakukan penambahan nilai secara bertahap (inkremental) untuk memastikan setiap ID bersifat unik.  
<img width="648" height="397" alt="auto generate id (sebelum)" src="https://github.com/user-attachments/assets/feec1e8c-f9d2-47bc-8b23-0bf5530930d9" />  
Dapat diperhatikan pada gambar di atas, ID Transaksi (TRX-001) tersebut merupakan kode unik dari setiap transaksi yang dilakukan. Ketika pengguna melakukan transaksi baru, Sistem akan membuat ID transaksi baru juga, sehingga setiap transaksi yang dilakukan selalu bersifat unik dan dapat diindentifikasi lebih baik.  
<img width="534" height="408" alt="Screenshot 2026-09-25 082151" src="https://github.com/user-attachments/assets/aa6674ed-a8a1-4b10-b307-b7f9e9863df9" />  

## D. **Penerapan Polymorphism**  
<img width="1101" height="444" alt="Polymorphism Transaksi java" src="https://github.com/user-attachments/assets/34f1fb41-d4ba-473d-b84f-9b619a68f210" />  

Pada class **Transaksi.java**, class ini berisi data yang disimpan yang digunakan untuk Menampilkan daftar transaksi yang memuat informasi gabungan antara data klien, paket yang dipilih, serta pencetakan rincian struk. Kelas ini menerapkan method overloading, yaitu dua method dengan nama sama **(cetakStruk)** namun memiliki parameter yang berbeda. Pemanggilan method **paket.detailLayanan()** mengeksekusi bentuk polymorphism berdasarkan tipe objek asli yang disimpan di dalam class **PaketReguler atau PaketEvent**.  
<img width="648" height="397" alt="Polymorpishm pada data client, paket,   rincian struk" src="https://github.com/user-attachments/assets/43dd77d0-1c6e-4d7d-8bdd-9c2f655c2d05" />  

## E. **Penerapan Encapsulation**  
<img width="537" height="269" alt="Encapsulation pada pengisian data client" src="https://github.com/user-attachments/assets/072f7540-7266-4fdd-9d4d-e0982fdff720" />  

Pada program Studio Fotografi ini, penerapan Encapsulation dibuat pada **class Client.java**, yang dimana pembungkusan data yang dilakukan ada pada saat pengisian dan penyimpanan formulir data klien pada saat melakukan penambahan transaksi baru.  
<img width="738" height="365" alt="Kode Encapsulation pada class client java" src="https://github.com/user-attachments/assets/5663b6b2-66e5-438e-83df-9b021c9ac4f5" />  
pada pembuatan encapsulation tersebut, Seluruh atribut dideklarasikan menggunakan tingkat akses **private**. Agar data atribut ini dapat dibaca dari luar class **Client.java** ini, dibuatlah method akses kontrol menggunakan **_getter_** (**getNamaClient()**, sehingga data yang dibaca tetap terjamin keamanan nya dan terjaga kerahasiaannya.  

## F. **Pembuatan Abstract Class**  
<img width="835" height="334" alt="Super-Class LayananFotografi java" src="https://github.com/user-attachments/assets/9188aaac-5893-4e98-b1b9-7c158b9e3bab" />  

Class Abstract **LayananFotografi** dibuat sebagai kerangka dasar (**blueprint**) untuk menyatukan atribut umum seperti kode, nama, dan harga paket, sekaligus mencegah instansiasi langsung atas objek yang belum spesifik.
Secara fungsi pada sistem, class **LayananFotografi** dibuat sebagai fondasi utama untuk mengelompokkan berbagai pilihan produk jasa foto di studio. class ini memastikan seluruh varian paket (seperti foto studio maupun dokumentasi acara) memiliki standar data pokok yang sama, yaitu kode, nama, dan harga layanan.  

Secara struktur kode, LayananFotografi merupakan sebuah Abstract Superclass yang dideklarasikan dengan kata kunci abstract agar tidak dapat diinstansiasi langsung **(new LayananFotografi())**. Atributnya menggunakan **access modifier _protected_** agar dapat diwarisi dan diakses secara langsung oleh subclass **PaketReguler** dan **PaketEvent**.  
Di dalamnya terdapat sebuah abstract method **_public abstract void detailLayanan();_** yang berfungsi sebagai kontrak struktur wajib. Method ini memaksa setiap subclass untuk melakukan **method overriding** guna mendefinisikan rincian spesifik masing-masing paket. Kelas ini juga memungkinkan penerapan polimorfisme, sehingga objek turunan yang berbeda dapat ditampung dalam satu koleksi tipe terpadu **(ArrayList<LayananFotografi>)**.  

## G. **Pembuatan Sub-Class _PaketReguler_ & _PaketEvent_**  
  1. **Sub-Class PaketReguler.java**
     <img width="943" height="360" alt="Sub-class PaketReguler" src="https://github.com/user-attachments/assets/be98777d-63bb-4a81-b787-2db1663f0469" />

     Pada program Studio Fotografi ini, class **PaketReguler.java** digunakan untuk mengelola data jasa fotografi kategori dalam ruangan **(indoor/studio)**, seperti foto wisuda atau foto keluarga. Kelas ini menambahkan informasi spesifik berupa durasi sesi foto **(dalam jam)**, sehingga sistem dapat menginformasikan batas waktu penggunaan studio secara jelas kepada klien saat memilih paket maupun saat mencetak nota pesanan.
     
     Secara struktur kode, **PaketReguler** bertindak sebagai **subclass** yang mewarisi sifat dari kelas induk melalui kata kunci **extends LayananFotografi**. Atribut tambahan durasiJam dideklarasikan menggunakan tingkat akses **_private_** (encapsulation). Constructor pada Class ini menggunakan fungsi **super()** untuk meneruskan data atribut dasar **(kodePaket, namaPaket, harga)** ke kelas induk. Kelas ini juga mengimplementasikan method **overriding (@Override)** pada fungsi **detailLayanan()** untuk menampilkan spesifikasi khusus berupa tipe studio dan durasi jam.

  3. **Sub-class PaketEvent.java**
     <img width="960" height="356" alt="Sub-class PaketEvent" src="https://github.com/user-attachments/assets/b4d07c7a-62b0-4058-9293-6bf084e9f9a3" />

     Pada Program ini, class **PaketEvent.java** digunakan untuk mengelola data jasa fotografi kategori luar ruangan **(outdoor/on-site)**, seperti dokumentasi acara pernikahan. class ini menambahkan informasi spesifik berupa jumlah kru (**fotografer/videografer**) yang akan diterjunkan ke lokasi acara, sehingga pihak manajemen studio dan klien memiliki kepastian terkait alokasi sumber daya manusia pada hari pelaksanaan sesi foto.

     Secara struktur kode, **PaketEvent** merupakan subclass yang juga diturunkan dari **LayananFotografi** menggunakan deklarasi **extends**. class ini memiliki atribut spesifik **jumlahKru** dengan menerapkan enkapsulasi **private**. Lewat pemanggilan _super()_ pada konstruktornya, nilai-nilai dari atribut dasar dikirim ke superclass. class ini men-_override_ method **detailLayanan()** dengan bentuk polimorfisme tersendiri, yaitu mencetak jenis layanan outdoor beserta jumlah kru yang bertugas.

## H. Penerapan Role Admin dan Klien  
Program kini dibagi menjadi dua jenis role dalam sistem, yaitu **role Admin** dan **role Klien**.  
Akun dengan **Role Admin** diatur agar dapat melakukan validasi pembayaran transaksi, melihat statistik penjualan, dan melihat semua _order_ jasa yang telah masuk

<img width="476" height="235" alt="Menu Atmin" src="https://github.com/user-attachments/assets/9a6798d2-1804-4bdf-8c83-3d010b593a30" />  

Sedangkan **Role Klien** diatur untuk dapat membuat pesanan java Fotografi dan memeriksa status pesanan tersebut.  

<img width="509" height="226" alt="Menu Klien" src="https://github.com/user-attachments/assets/7d3628fc-a488-4316-a8d6-1da8e8babea9" />  

## I. Menerapkan Keyword Final pada Abstract Method  

<img width="863" height="310" alt="Final Pada Abstract method" src="https://github.com/user-attachments/assets/9705de0b-19ab-457b-a914-92bdecfeefc8" />  

Keyword Final di atas diterapkan pada class **LayananFotografi.java** yang merupakan salah satu Abstract class dalam program ini. Keyword final tersebut berfungsi agar Method pemanggil informasi jenis layanan tidak akan mengalami _**override**_ saat dipanggil oleh sub class.  

## J. Penambahan Class Baru Dalam Program  

<img width="337" height="405" alt="Class baru utk Minpro 3" src="https://github.com/user-attachments/assets/889402b0-d1db-4f53-b628-a55186643d7d" />  

Karena adanya penambahan beberapa fitur baru di dalam program, maka ditambahkan pula beberapa class baru yang digunakan untuk mengakomodir kebutuhan program tersebut, yaitu class **Akun, Admin, ClientVIP, dan** **PotonganHarga**.  
  - **Class Akun**        : adalah class induk yang berisi atribut umum dari seluruh pengguna sistem seperti admin dan klien.
  - **Class Admin**       : adalah sub-class dari **AKUN** yang berfungsi untuk mengakomodasi kebutuhan role admin yang bertugas mengelola transaksi jasa studio fotografi ini.
  - **Class ClientVIP**   : adalah sub-class baru dari class **Client**. Bedanya dari class client ada pada jenis pelanggan nya, dimana client dikategorikan sebagai pelanggan reguler, sedangkan clientVIP dikategorikan sebagai pelanggan khusus, sehingga memiliki kesempatan mendapatkan diskon harga jasa.
  - **class PotonganHarga** : Merupakan interface baru untuk menghitung diskon khusus pelanggan dengan kategori VIP.

## K. Penerapan Polymorphism  

<img width="458" height="102" alt="Screenshot 2026-10-09 024439" src="https://github.com/user-attachments/assets/d7beec17-0602-472e-8ca6-b623dd0062f6" />  

**A. Polymorphism Overriding**  
Polymorphism terjadi pada method **tampilkanPeran()** yang berada di class **Akun** selaku class induk dari **Admin, Client** dan **ClientVIP**. Method tersebut berfungsi untuk menampilkan keterangan **Role** dari akun yang sedang digunakan user saat user membuka program.  

<img width="535" height="141" alt="Screenshot 2026-10-09 024753" src="https://github.com/user-attachments/assets/fd2e2fcb-df26-4849-a056-204709651b0a" />  

Misal pada class **Client**, Method di override agar dapat menampilkan pesan jenis role dari akun yang sedang digunakan pengguna, yaitu Klien Reguler.  

**B. Polymorphism Overloading**  
Overloading disini terjadi pada method **cetakStruk()** yang berada di class **Transaksi**.  

<img width="998" height="298" alt="Overloading" src="https://github.com/user-attachments/assets/723635b9-4ca6-4c1c-96f8-823ef3451918" />  

Overloading disini memungkinkan method untuk dapat mencetak transaksi yang dilakukan secara lebih detail sesuai kebutuhan.  

## L. Penerapan Abstraction  
**A. Abstract Class**  

<img width="498" height="299" alt="Abstract Class" src="https://github.com/user-attachments/assets/e935e066-9ea4-4c8b-a065-3f59bd785224" />  

Contoh lain dari penerapan Abstract class ada pada class **Akun.java**, yang dimana class ini merupakan kelas induk yang menjadi dasar dari atribut kelas anak-anaknya. Kelas ini hanya boleh diinstansiasi secara umumnya saja, dan wajib diimplementasikan rincian lebih lanjut oleh sub-class nya.  

**M. Abstract Method**  

<img width="854" height="323" alt="Abstract Method" src="https://github.com/user-attachments/assets/9ee463b5-7bab-4dbe-8c0d-0009bafff9a7" />  

Penerapan Abstract Method ini terdapat pada class **LayananFotografi**. Pada method **detailLayanan()** fungsinya adalah memanggil informasi umum mengenai jenis layanan jasa yang terdapat dalam program. Karena sifatnya Abstrak, maka method tersebut dapat diwariskan ke sub-class lain, seperti sub-class **PaketEvent** untuk menambahkan detail layanan misal tipe layanan (outdoor/on-site) dan jumlah kru yang diinginkan.  

## N. Penerapan Interface  

<img width="832" height="265" alt="Interface" src="https://github.com/user-attachments/assets/a1aa586d-f18d-4743-a43b-724bf563f5ef" />  

Secara fungsi bisnis dalam sistem, Interface **PotonganHarga** berfungsi untuk mengakomodasi fitur keanggotaan khusus (VIP). Interface ini bertindak sebagai mekanisme otomatis yang memberikan potongan harga (diskon sebesar 15%) kepada pelanggan VIP saat melakukan pemesanan paket fotografi.  
Dengan adanya interface ini, sistem dapat secara fleksibel membedakan perhitungan total tagihan antara klien reguler dan klien VIP tanpa perlu merusak logika pembayaran dasar yang sudah ada.
