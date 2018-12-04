<?php

class Encryption
{
    private $encryptedData;
    private $iv;
    private $secretKey;
    const AES = "AES-256-CBC";
    // private const RSA = "";
    // AES-256 Encryption
    public function setKey($key) { $secretKey = $key; }
    public function encrypt($data) { return openssl_encrypt($data, "AES-256-CBC", $secretKey, $iv); }
    // string openssl_decrypt ( string $data , string $method , string $key [, int $options = 0 [, string $iv = "" [, string $tag = "" [, string $aad = "" ]]]] )
    public function decrypt($data, $iv)
    {
        //$byte_array = unpack('C*', 'The quick fox jumped over the lazy brown dog');
        //var_dump($byte_array);  // $byte_array should be int[] which can be converted
                                // to byte[] in C# since values are range of 0 - 255
                                
        return openssl_decrypt($encryptedData, "AES-256-CBC", $secretKey, $data, $iv);
    }
    
    // RSA 2048 Encryption
    public function transport()
    {}
    
    public function receive()
    {}
    
    public function passwordChecking()
    {}
}

?>
