import { Component, OnInit } from '@angular/core';

import { GalleryImage, GalleryOptions } from 'ontimize-web-ngx-gallery';

@Component({
  selector: 'app-bands-home',
  templateUrl: './bands-home.component.html',
  styleUrls: ['./bands-home.component.scss']
})
export class BandsHomeComponent implements OnInit {

 

  constructor() { }
  videos: string[] = [
    'SkypZuY6ZvA',
    '_N5YIGT_R58',
    'Ld0NDDYXj-c',
    'uelHwf8o7_U'
  ];

  galleryOptions1: GalleryOptions[];
  galleryOptions2: GalleryOptions[];
  galleryOptions3: GalleryOptions[];
  galleryOptions4: GalleryOptions[];
  galleryOptions5: GalleryOptions[];
  galleryOptions6: GalleryOptions[];
  galleryImages: GalleryImage[];

  ngOnInit(): void {
    this.galleryOptions1 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
      }
    ];
  
    this.galleryOptions2 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
        image: true,
        thumbnails: false,
        preview: false
      }
    ];
  
    this.galleryOptions3 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
        image: true,
        thumbnails: false,
        preview: true
      }
    ];
  
    this.galleryOptions4 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
        image: false,
        thumbnails: true
      }
    ];
  
    this.galleryOptions5 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
        image: true,
        imageArrows: true,
        thumbnails: true,
        preview: true,
        previewKeyboardNavigation: false,
        previewDownload: false,
        previewRotate: false,
        previewZoom: false,
        previewDescription: false,
        previewFullscreen: false
      }
    ];
  
    this.galleryOptions6 = [
      {
        breakpoint: 1920,
        height: "200px",
        width: "300px",
        preview: true,
        previewAutoPlay: true
      }
    ];
  
    this.galleryImages = [
      {
        small: 'https://picsum.photos/id/1011/150/100.jpg',
        medium: 'https://picsum.photos/id/1011/600/450.jpg',
        big: 'https://picsum.photos/id/1011/600/450.jpg'
      },
      {
        small: 'https://picsum.photos/id/1012/150/100.jpg',
        medium: 'https://picsum.photos/id/1012/600/450.jpg',
        big: 'https://picsum.photos/id/1012/600/450.jpg'
      },
      {
        small: 'https://picsum.photos/id/1020/150/100.jpg',
        medium: 'https://picsum.photos/id/1020/600/450.jpg',
        big: 'https://picsum.photos/id/1020/600/450.jpg'
      },
      {
        small: 'https://picsum.photos/id/1023/150/100.jpg',
        medium: 'https://picsum.photos/id/1023/600/450.jpg',
        big: 'https://picsum.photos/id/1023/600/450.jpg'
      },
      {
        small: 'assets/images/i_video.jpg',
        medium: 'assets/videos/video1.mp4',
        big: 'assets/videos/video1.mp4'
      }
    ];
  }
      


}