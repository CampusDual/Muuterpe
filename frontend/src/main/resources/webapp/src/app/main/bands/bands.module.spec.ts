import { BandsModule } from './bands.module';

describe('BandsModule', () => {
  let bandsModule: BandsModule;

  beforeEach(() => {
    bandsModule = new BandsModule();
  });

  it('should create an instance', () => {
    expect(bandsModule).toBeTruthy();
  });
});
