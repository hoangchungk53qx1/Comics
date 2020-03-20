package com.example.comics.callback;

public interface GetTruyen {
    void Start();
    void Complemet(String data); // khi kết thúc lấy được data về, truyền vào 1 thằng data để bên house xử lý
    void Error();
    // *1 là kiểu dữ liệu nhận về
    // *2 là tên phương thức
    // *3 là kiểu dữ liệu muốn gửi
}
