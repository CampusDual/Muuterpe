import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({ name: 'safe' })

export class SafePipe implements PipeTransform {
    constructor(private sanitizer: DomSanitizer) { }
    transform(video: string) {
        const apiKey: string = 'AIzaSyAu6w84GsPQm5U2wzapo7aShRjDjlnQnwk';
        let url = 'https://www.youtube.com/embed/' + video + '?autoplay=0&fs=0&iv_load_policy=3&showinfo=0&rel=0&cc_load_policy=0&start=0&end=0';


        return this.sanitizer.bypassSecurityTrustResourceUrl(url);


    }
}