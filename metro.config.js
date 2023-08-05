const { getDefaultConfig } = require('metro-config');

module.exports = (async () => {
  const {
    resolver: { sourceExts, assetExts },
  } = await getDefaultConfig();
  const extendedSourceExts = [...sourceExts, 'svg'];

  return {
    transformer: {
      babelTransformerPath: require.resolve('react-native-svg-transformer'),
    },
    transform: {
      experimentalImportSupport: false,
      inlineRequires: true,
    },
    resolver: {
      assetExts: [...assetExts.filter((ext) => ext !== 'svg'), 'xsvg'],
      sourceExts: extendedSourceExts,
    },
  };
})();
