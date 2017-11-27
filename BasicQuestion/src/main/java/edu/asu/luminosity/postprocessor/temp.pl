#!/usr/bin/perl  
use LWP::Simple;
no warnings;

use Getopt::Long;
my $data;
GetOptions ("topic|t=s" => \$data)  
or die("Error in command line arguments\n");


$keyword=$data;
$keyword = lc $keyword;
$keyword = ucfirst $keyword;	


#L27-L34(CONVERSION OF INPUT INTO PROPER FORMAT TO EMBED FOR THE WIKIPEDIA) 
@UPkeyword =(split / / ,$keyword);
foreach $key (@UPkeyword)
{
	$key= ucfirst $key;
	push(@keywordUP,$key);
}
$keyword= join( '_' , @keywordUP);	



#L52-L60(EXTRACTING WEB PAGE DATA )
my $URL ="https://en.wikipedia.org/wiki/";
$URL = $URL . $keyword;
my $content = get $URL;
if(not defined $content)
{
	die "Couldn't get $URL" ;
	exit;
}

@list=split("\n",$content);
$list2="";
foreach (@list) 
{
		if ($_ =~ m/>Contents</)
		{
			next;
		
		}
	if($_ =~ m/<h2>(.+)>See also<\/span>(.+)<\/h2>/)
		{
			last;
		}
	if($_ =~ m/<title>.*<\/title>/ || m/<h.*<\/h.*>/ || m/<p>.*<\/p>/ || m/<li>.*<\/li>/)
	{
	#	while($_ =~ m/<a href.*>/ || $_ =~ m/<\/a>/ || $_ =~ m/<span[^>]*>/ || m/<\/span>/ || m/<sup>.*<\/sup>/ || m/<sup[^w]*>/ || m/<\/sup>/ ) 
		{
			$_ =~ s/<a href[^>]*">//g;
			$_ =~ s/<\/a>//g;
			$_ =~ s/<span[^>]*>//g;
			$_ =~ s/<\/span>//g;
			$_ =~ s/<sup.*?<\/sup>//g;
			$_ =~ s/<sup[^w]*>//g;
			$_ =~ s/<\/sup>//g;
			$_ =~ s/<a rel[^>]*>//g;
			$_ =~ s/<img[^>]*>//g;
			$_ =~ s/\[.*\]//g;
			$_ =~ s/<p>//g;
			$_ =~ s/<i>//g;
			$_ =~ s/<\/i>//g;
			$_ =~ s/<b>//g;
			$_ =~ s/<\/b>//g;
			$_ =~ s/<p>//g;
			$_ =~  s/<tt>//g;
			$_ =~  s/<\/tt>//g;
			$_ =~ s/<div[^>]*>//g;
			$_ =~ s/<code>.*?<\/code>//g;
			$_ =~ s/<small.*?<\/small>//g;
		}
		$list2 = $list2 . $_ ."\n";
	}
}
1;
@list2=split("\n",$list2);
$ANSWER="";
$count =0;
foreach (@list2) 
{
	if($count > 1)
	{
		last;
	}
	if($_ =~ m/<h[^>]*>/)
	{
		$count=$count+1;
		if($' =~ m/<\/h.*>/)
		{
			if($` =~ m/External links/i)
			{
				last;
			}
		}
	}	
	if($_ =~ m/<\/p>/)
	{
		if($count > 0)
		{
			$ANSWER= $ANSWER . "$`";
			last;
		}
	}
}
@ANSWER=split("\n",$ANSWER);
foreach (@ANSWER) 
{
	print  $_;

}
