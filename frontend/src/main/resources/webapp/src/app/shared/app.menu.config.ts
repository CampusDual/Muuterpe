import { MenuRootItem } from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  {id: 'bands', name: 'BANDS', icon: 'home', route: '/main/bands' },
  {id: 'events', name: 'EVENTS', icon: 'home', route: '/main/events' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
