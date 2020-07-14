import { MenuRootItem } from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'MUUTERPE', icon: 'headset', route: '/main/home' },
  { id: 'bands', name: 'BANDS', icon: '', route: '/main/bands' },
  { id: 'events', name: 'EVENTS', icon: '', route: '/main/events' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }

];


