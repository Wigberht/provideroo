<script type="text/x-template" id="page-link">
    <li v-if="command!='undefined'">
        <form method="post" action="MainController">
            <input type="hidden" name="command" :value="command">
            <input type="submit" :value="title">
        </form>
    </li>
    <li v-else>
        <a :href="link">{{title}}</a>
    </li>
</script>

<script>
    Vue.component('page-link', {
        props: [
            'link',
            'title',
            'command'
        ],
        template: "#page-link"
    })
</script>
