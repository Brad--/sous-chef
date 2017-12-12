import { SousChefUiPage } from './app.po';

describe('sous-chef-ui App', function() {
  let page: SousChefUiPage;

  beforeEach(() => {
    page = new SousChefUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
