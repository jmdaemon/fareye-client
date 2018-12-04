<?php

require ("encrypt.php");
$includedFiles = get_included_files();

foreach ($includedFiles as $filename)
{
    echo "$filename\n";
}

?>

