import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:graphql_flutter/graphql_flutter.dart';
// import 'package:url_launcher/url_launcher.dart';

const productGraphQL = """
query products{
  products(first: 10, channel: "default-channel") {
    edges {
      node {
        id
        name
        description
        thumbnail{
          url
        }
      }
    }
  }
  }
""";

void main() {
  final HttpLink httpLink = HttpLink("https://demo.saleor.io/graphql/");

  ValueNotifier<GraphQLClient> client = ValueNotifier(
    GraphQLClient(
      link: httpLink,
      cache: GraphQLCache(store: InMemoryStore()),
    ),
  );

  var app = GraphQLProvider(
    client: client,
    child: MyApp(),
  );
  WidgetsFlutterBinding.ensureInitialized();
  runApp(app);
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: _MyHomePage(),
    );
  }
}

class _MyHomePage extends StatefulWidget {
  @override
  State<_MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<_MyHomePage> {
  static const platform =
      MethodChannel("com.example.native_app/method_channel");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Page'),
      ),
      body: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          ElevatedButton(
            onPressed: () async {
              try {
                await platform.invokeMethod('getDataFromNative');
              } catch (e) {
                print('Error invoking getDataFromNative: $e');
              }
            },
            child: const Text('Open Native Page via Method Channel'),
          ),
          // Query(
          //   options: QueryOptions(document: gql(productGraphQL)),
          //   builder: (QueryResult result, {fetchMore, refetch}) {
          //     if (result.hasException) {
          //       return Text(result.exception.toString());
          //     }
          //     if (result.isLoading) {
          //       return Center(
          //         child: CircularProgressIndicator(),
          //       );
          //     }

          //     final productList = result.data?['products']['edges'];
          //     print(productList.toString());

          //     return Text(productList.toString());
          //   },
          // ),
        ],
      ),
    );
  }
}
