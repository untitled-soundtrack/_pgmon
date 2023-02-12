export const upgradeElement = function (element) {
  return new Promise((resolve) => {
    if (window.componentHandler) {
      window.componentHandler.upgradeElement(element);
      resolve();
    } else {
      window.setTimeout(upgradeElement, 100, element);
    }
  });
};
