package com.example.comics.callback;

public interface GetAnh {
    void StartLoadingImage();
    void CompleteLoadingImage(String link);
    void Error();
}
